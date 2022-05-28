package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import demo.domain.Discussqiuzhizhexinxi;
import demo.domain.Qiuzhizhexinxi;
import demo.mapper.DiscussqiuzhizhexinxiMapper;
import demo.mapper.QiuzhizhexinxiMapper;
import demo.service.IDiscussqiuzhizhexinxiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 求职者信息评论表 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class DiscussqiuzhizhexinxiServiceImpl extends ServiceImpl<DiscussqiuzhizhexinxiMapper, Discussqiuzhizhexinxi> implements IDiscussqiuzhizhexinxiService {


    @Autowired
    private DiscussqiuzhizhexinxiMapper discussqiuzhizhexinxiMapper;

    @Autowired
    private QiuzhizhexinxiMapper qiuzhizhexinxiMapper;

    @Override
    public Result add(Discussqiuzhizhexinxi discussqiuzhizhexinxi, BigInteger yonghuId) {
        Qiuzhizhexinxi qiuzhizhexinxi = qiuzhizhexinxiMapper.selectById(discussqiuzhizhexinxi.getRefid());
        if (Objects.isNull(qiuzhizhexinxi)){
            return new Result(Code.SAVE_ERR,"该求职信息不存在，添加评论失败");
        }
        discussqiuzhizhexinxi.setD_id(null);
        discussqiuzhizhexinxi.setAddtime(TimeUtils.getTime());
        discussqiuzhizhexinxi.setUserid(yonghuId);
        discussqiuzhizhexinxiMapper.insert(discussqiuzhizhexinxi);
        return new Result(Code.SAVE_OK, discussqiuzhizhexinxi, "发布求职者评论信息成功");
    }

    @Override
    public Result deleteSelf(BigInteger id, BigInteger yonghuId) {
        if (!check(id, yonghuId)){
            return new Result(Code.DELETE_ERR,"删除求职者信息评论失败");
        }
        int i = discussqiuzhizhexinxiMapper.deleteById(id);
        if (i==0){
            return new Result(Code.DELETE_ERR,"删除求职者信息评论失败");
        }

        return new Result(Code.DELETE_ERR,"删除求职者信息评论成功");
    }

    @Override
    public Result updateSelf(Discussqiuzhizhexinxi discussqiuzhizhexinxi, BigInteger yonghuId, BigInteger id) {
        // 修改自己已经评论过的评论
        LambdaUpdateWrapper<Discussqiuzhizhexinxi> uw = new LambdaUpdateWrapper<>();
        uw.eq(Discussqiuzhizhexinxi::getUserid, yonghuId);
        uw.eq(Discussqiuzhizhexinxi::getRefid, discussqiuzhizhexinxi.getRefid());
        uw.eq(Discussqiuzhizhexinxi::getD_id,id);
        int update = discussqiuzhizhexinxiMapper.update(discussqiuzhizhexinxi, uw);
        if (update == 0) {  // 不是自己的评论 or 未对该求职者信息评论
            return new Result(Code.UPDATE_ERR, "修改评论失败");
        }
        return new Result(Code.UPDATE_OK, discussqiuzhizhexinxi.getContent(), "修改求职者评论成功");
    }

    @Override
    public Result selectSelf(BigInteger yonghuId) {
        //查看自己的评论
        List<Qiuzhizhexinxi> qiuzhizhexinxiList=discussqiuzhizhexinxiMapper.selectSelf(yonghuId);
        if (qiuzhizhexinxiList.isEmpty()){
            return new Result(Code.GET_ERR, "您还未有求职者信息评论");
        }
        return new Result(Code.GET_OK, qiuzhizhexinxiList, "查看自己求职者评论成功");
    }

    @Override
    public Result select(BigInteger r_id) {
        List<Qiuzhizhexinxi> qiuzhizhexinxiList=discussqiuzhizhexinxiMapper.select(r_id);
        if (qiuzhizhexinxiList.isEmpty()){
           return new Result(Code.GET_ERR, "未有求职者信息评论");
        }
        return new Result(Code.GET_OK, qiuzhizhexinxiList, "查看求职者评论成功");
    }

    public boolean check(BigInteger id, BigInteger yonghuId){
        Qiuzhizhexinxi qiuzhizhexinxi=discussqiuzhizhexinxiMapper.check(id,yonghuId);
        return Objects.nonNull(qiuzhizhexinxi);
    }
}
