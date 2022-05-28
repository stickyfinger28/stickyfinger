package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import demo.domain.L.LQiuzhizhexinxi;
import demo.domain.Qiuzhizhexinxi;
import demo.domain.Xuelixinxi;
import demo.domain.Yonghu;
import demo.mapper.QiuzhizhexinxiMapper;
import demo.mapper.XuelixinxiMapper;
import demo.service.IQiuzhizhexinxiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 求职者信息 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class QiuzhizhexinxiServiceImpl extends ServiceImpl<QiuzhizhexinxiMapper, Qiuzhizhexinxi> implements IQiuzhizhexinxiService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private QiuzhizhexinxiMapper qiuzhizhexinxiMapper;

    @Autowired
    private XuelixinxiMapper xuelixinxiMapper;

    @Override //添加求职信息 //TODO 赋予staff角色
    public Result add(Qiuzhizhexinxi qiuzhizhexinxi, Yonghu yonghu) {
        Xuelixinxi xuelixinxi = xuelixinxiMapper.selectById(yonghu.getId());
        if (Objects.isNull(xuelixinxi)){
            return new Result(Code.SAVE_ERR,"你还未添加学历信息，请先添加学历信息");
        }
        qiuzhizhexinxiMapper.setStaff(yonghu.getId());
        qiuzhizhexinxi.setXueli(xuelixinxi.getXueli());
        qiuzhizhexinxi.setAddtime(TimeUtils.getTime());
        qiuzhizhexinxi.setXingming(yonghu.getXingming());
        qiuzhizhexinxi.setYonghuming(yonghu.getYonghuming());
        qiuzhizhexinxi.setXingbie(yonghu.getXingbie());
        qiuzhizhexinxi.setDianhua(yonghu.getDianhua());
        qiuzhizhexinxiMapper.insert(qiuzhizhexinxi);
        return new Result(Code.SAVE_OK,qiuzhizhexinxi,"添加成功");
    }



    @Override //修改求职信息
    public Result updateSelf(LQiuzhizhexinxi lQiuzhizhexinxi, Yonghu yonghu) {
        if(!check(lQiuzhizhexinxi.getId(),yonghu.getYonghuming())){
            return new Result(Code.DELETE_ERR,"求职信息验证失败失败");
        }
        qiuzhizhexinxiMapper.updateSelf(lQiuzhizhexinxi);
        return new Result(Code.UPDATE_OK,"修改求职信息成功");
    }

    @Override
    public Result selectAll() {
        QueryWrapper<Qiuzhizhexinxi> qw = new QueryWrapper<>();
        List<Qiuzhizhexinxi> list = qiuzhizhexinxiMapper.selectList(qw);
        return new Result(Code.GET_OK,list,"查询求职信息成功");
    }

    @Override
    public Result dianzhan(Integer id, BigInteger yonghuId) {
        //判断传入的id是否存在
        Qiuzhizhexinxi qiuzhizhexinxi = qiuzhizhexinxiMapper.selectById(id);
        if (Objects.isNull(qiuzhizhexinxi)){
            return new Result(Code.UPDATE_ERR,"点赞失败");
        }

        //获取当时的点赞数
        Integer  t = qiuzhizhexinxi.getThumbsupnum();
        String key="qiuzhilike";
        String hashKey=id.toString()+":"+yonghuId.toString();
        Object like = stringRedisTemplate.opsForHash().get(key, hashKey);
        //没有进行点赞操作
        if (Objects.isNull(like)){
            //将对应的操作信息存入redis中 id：yonghuID:1 表示用户对该id点赞
            stringRedisTemplate.opsForHash().put(key,hashKey,"1");
          qiuzhizhexinxiMapper.dianzhan(id,t+1);
        }
        //如果之前有进行点赞操作 查询操作状态
        //之前点赞 -->取消点赞
        if (Objects.equals(like, "1")){
            stringRedisTemplate.opsForHash().put(key,hashKey,"0");
            qiuzhizhexinxiMapper.dianzhan(id,t-1);
        }else {
            //恢复点赞
            stringRedisTemplate.opsForHash().put(key,hashKey,"1");
           qiuzhizhexinxiMapper.dianzhan(id,t+1);
        }
        Integer thumbsupnum = qiuzhizhexinxiMapper.selectById(id).getThumbsupnum();
        return new Result(Code.UPDATE_OK,thumbsupnum,"操作成功");
    }

    @Override
    public Result diancai(Integer id, BigInteger yonghuId) {
        //判断传入的id是否存在
        Qiuzhizhexinxi qiuzhizhexinxi = qiuzhizhexinxiMapper.selectById(id);
        if (Objects.isNull(qiuzhizhexinxi)){
            return new Result(Code.UPDATE_ERR,"点踩失败");
        }

        //获取当时的点踩数
        Integer c = qiuzhizhexinxi.getCrazilynum();
        String key="qiuzhidislike";
        String hashKey=id.toString()+":"+yonghuId.toString();
        Object like = stringRedisTemplate.opsForHash().get(key, hashKey);
        //没有对该进行点赞操作
        if (Objects.isNull(like)){
            //将对应的操作信息存入redis中 id：yonghuID:1 表示用户对该id点赞
            stringRedisTemplate.opsForHash().put(key,hashKey,"1");
            qiuzhizhexinxiMapper.diancai(id,c+1);
        }
        //如果之前有进行点赞操作 查询操作状态
        //之前点赞 -->取消点赞
        if (Objects.equals(like, "1")){
            stringRedisTemplate.opsForHash().put(key,hashKey,"0");
            qiuzhizhexinxiMapper.diancai(id,c-1);
        }else {
            //恢复点赞
            stringRedisTemplate.opsForHash().put(key,hashKey,"1");
            qiuzhizhexinxiMapper.diancai(id,c+1);
        }
        Integer crazilynum = qiuzhizhexinxiMapper.selectById(id).getCrazilynum();
        return new Result(Code.UPDATE_OK,crazilynum,"操作成功");
    }

    @Override
    public Result delete(BigInteger id, Yonghu yonghu) {
        if(!check(id,yonghu.getYonghuming())){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        int i = qiuzhizhexinxiMapper.deleteById(id);
        if (i==0){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        return new Result(Code.DELETE_OK,"删除求职者信息成功");
    }



    @Override //查询求职信息
    public Result selectSelf(Yonghu yonghu) {
        String yonghuming = yonghu.getYonghuming();
        List<Qiuzhizhexinxi> qiuzhizhexinxiList = qiuzhizhexinxiMapper.selectSelf(yonghuming);
        if (Objects.isNull(qiuzhizhexinxiList)){
            return new Result(Code.GET_ERR,"您还未上传求职者信息");
        }
        return new Result(Code.GET_OK,qiuzhizhexinxiList,"查询成功");
    }

    public Boolean check(BigInteger id,String yonghuming){
        Qiuzhizhexinxi qiuzhizhexinxi=qiuzhizhexinxiMapper.check(id,yonghuming);
        return Objects.nonNull(qiuzhizhexinxi);
    }

}
