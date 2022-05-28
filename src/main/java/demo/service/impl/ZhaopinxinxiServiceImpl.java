package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import demo.domain.L.LZhaopinxinxi;
import demo.domain.Qiuzhizhexinxi;
import demo.domain.Qiyexinxi;
import demo.domain.Yonghu;
import demo.domain.Zhaopinxinxi;
import demo.mapper.QiyexinxiMapper;
import demo.mapper.ZhaopinxinxiMapper;
import demo.service.IQiyexinxiService;
import demo.service.IZhaopinxinxiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 招聘信息 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class ZhaopinxinxiServiceImpl extends ServiceImpl<ZhaopinxinxiMapper, Zhaopinxinxi> implements IZhaopinxinxiService {
    @Autowired
    private IQiyexinxiService qiyexinxiService;

    @Autowired
    private ZhaopinxinxiMapper zhaopinxinxiMapper;

    @Autowired
    private QiyexinxiMapper qiyexinxiMapper;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override  //企业编号对应的负责人添加
    public Result add(@Valid Zhaopinxinxi zhaopinxinxi, Yonghu boss) {
        //查看该用户名下是否有企业
        String qiyebianhao = zhaopinxinxi.getQiyebianhao();
        LambdaQueryWrapper<Qiyexinxi> qw = new LambdaQueryWrapper<>();
        qw.eq(Qiyexinxi::getQiyebianhao,qiyebianhao);
        qw.eq(Qiyexinxi::getUserid,boss.getId());
        if (Objects.isNull(qiyexinxiMapper.selectOne(qw))){
            return new Result(Code.GET_ERR,"企业信息验证错误");
        }

        //调用方法保存到数据库
        zhaopinxinxi.setAddtime(TimeUtils.getTime());
        zhaopinxinxiMapper.insert(zhaopinxinxi);
        return new Result(Code.SAVE_OK,zhaopinxinxi,"添加招聘信息成功");
    }

    @Override
    public Result delete(BigInteger id, BigInteger yonghuId) {
        Boolean cheak = cheak(id, yonghuId);
        if (!cheak){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        int i = zhaopinxinxiMapper.deleteById(id);
        if (i==0){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        return new Result(Code.DELETE_OK,"删除招聘信息成功");
    }

    @Override
    public Result updateSelf(LZhaopinxinxi lZhaopinxinxi,Yonghu yonghu) {
        Boolean cheak = cheak(lZhaopinxinxi.getId(), yonghu.getId());
        if (!cheak){
            return new Result(Code.UPDATE_ERR,"招聘信息验证失败");
        }
        //调用方法更改信息
        zhaopinxinxiMapper.updateSelf(lZhaopinxinxi);
        return new Result(Code.UPDATE_OK,"修改招聘信息成功");
    }

    @Override
    public Result select() {
        List<Zhaopinxinxi> zhaopinxinxiList =zhaopinxinxiMapper.select();
        if(zhaopinxinxiList.isEmpty()){
            return new Result(Code.GET_ERR,"无任何招聘信息");
        }
        return new Result(Code.GET_OK,zhaopinxinxiList,"查看招聘信息成功");
    }

    @Override
    public Result selectSelf(Yonghu boss) {
        //查看该用户名下是否有企业
        Object data = qiyexinxiService.selectSelf(boss).getData();
        if (Objects.isNull(data)){
            return new Result(Code.GET_ERR,"你还未成为企业负责人，请前往注册");
        }
        BigInteger bossId = boss.getId();
        List<Zhaopinxinxi >zhaopinxinxiList=zhaopinxinxiMapper.selectSelf(bossId);
        if(zhaopinxinxiList.isEmpty()){
            return new Result(Code.GET_ERR,"你还未添加任何招聘信息");
        }

        return new Result(Code.GET_OK,zhaopinxinxiList,"查看自己招聘信息成功");
    }

    @Override
    public Result dianzhan(Integer id, BigInteger yonghuId) {
        //判断传入的id是否存在
        Zhaopinxinxi zhaopinxinxi = zhaopinxinxiMapper.selectById(id);
        if (Objects.isNull(zhaopinxinxi)){
            return new Result(Code.UPDATE_ERR,"点赞失败");
        }

        //获取当时的点赞数
        Integer  t = zhaopinxinxi.getThumbsupnum();
        String key="zhaopinlike";
        String hashKey=id.toString()+":"+yonghuId.toString();
        Object like = stringRedisTemplate.opsForHash().get(key, hashKey);
        //没有对该进行点赞操作
        if (Objects.isNull(like)){
            //将对应的操作信息存入redis中 id：yonghuID:1 表示用户对该id点赞
            stringRedisTemplate.opsForHash().put(key,hashKey,"1");
            zhaopinxinxiMapper.dianzhan(id,t+1);
        }
        //如果之前有进行点赞操作 查询操作状态
        //之前点赞 -->取消点赞
        if (Objects.equals(like, "1")){
            stringRedisTemplate.opsForHash().put(key,hashKey,"0");
            zhaopinxinxiMapper.dianzhan(id,t-1);
        }else {
            //恢复点赞
            stringRedisTemplate.opsForHash().put(key,hashKey,"1");
            zhaopinxinxiMapper.dianzhan(id,t+1);
        }
        Integer thumbsupnum = zhaopinxinxiMapper.selectById(id).getThumbsupnum();
        return new Result(Code.UPDATE_OK,thumbsupnum,"操作成功");
    }

    @Override
    public Result diancai(Integer id, BigInteger yonghuId) {
        //判断传入的id是否存在
        Zhaopinxinxi zhaopinxinxi = zhaopinxinxiMapper.selectById(id);
        if (Objects.isNull(zhaopinxinxi)){
            return new Result(Code.UPDATE_ERR,"点踩失败");
        }

        //获取当时的点踩数
        Integer c = zhaopinxinxi.getCrazilynum();
        String key="zhaopindislike";
        String hashKey=id.toString()+":"+yonghuId.toString();
        Object like = stringRedisTemplate.opsForHash().get(key, hashKey);
        //没有进行点赞操作
        if (Objects.isNull(like)){
            //将对应的操作信息存入redis中 id：yonghuID:1 表示用户对该id点赞
            stringRedisTemplate.opsForHash().put(key,hashKey,"1");
            zhaopinxinxiMapper.diancai(id,c+1);
        }
        //如果之前有进行点赞操作 查询操作状态
        //之前点赞 -->取消点赞
        if (Objects.equals(like, "1")){
            stringRedisTemplate.opsForHash().put(key,hashKey,"0");
            zhaopinxinxiMapper.diancai(id,c-1);
        }else {
            //恢复点赞
            stringRedisTemplate.opsForHash().put(key,hashKey,"1");
            zhaopinxinxiMapper.diancai(id,c+1);
        }
        Integer crazilynum = zhaopinxinxiMapper.selectById(id).getCrazilynum();
        return new Result(Code.UPDATE_OK,crazilynum,"操作成功");
    }


    public Boolean cheakByQiyebianhao(String qiyebianhao){
        LambdaQueryWrapper<Qiyexinxi> qw = new LambdaQueryWrapper<>();
        qw.eq(Qiyexinxi::getQiyebianhao,qiyebianhao);
        return Objects.nonNull(qiyexinxiMapper.selectOne(qw));
    }


    //判断传入id是否为操作用户的招聘信息
    public Boolean cheak(BigInteger zhaopinxinxiId,BigInteger yonghuId){
        Zhaopinxinxi zhaopinxinxi = zhaopinxinxiMapper.check(zhaopinxinxiId, yonghuId);
        return Objects.nonNull(zhaopinxinxi);
    }

}
