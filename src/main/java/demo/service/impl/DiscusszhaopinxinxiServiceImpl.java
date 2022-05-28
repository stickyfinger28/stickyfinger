package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import demo.domain.Discusszhaopinxinxi;
import demo.domain.Zhaopinxinxi;
import demo.mapper.DiscusszhaopinxinxiMapper;
import demo.mapper.ZhaopinxinxiMapper;
import demo.service.IDiscusszhaopinxinxiService;
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
 * 招聘信息评论表 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class DiscusszhaopinxinxiServiceImpl extends ServiceImpl<DiscusszhaopinxinxiMapper, Discusszhaopinxinxi> implements IDiscusszhaopinxinxiService {

    @Autowired
    private DiscusszhaopinxinxiMapper discusszhaopinxinxiMapper;

    @Autowired
    private ZhaopinxinxiMapper zhaopinxinxiMapper;

    @Override
    public Result add(Discusszhaopinxinxi discusszhaopinxinxi, BigInteger yonghuId) {
        Zhaopinxinxi zhaopinxinxi = zhaopinxinxiMapper.selectById(discusszhaopinxinxi.getRefid());
        if (Objects.isNull(zhaopinxinxi)){
            return new Result(Code.SAVE_ERR,"该招聘信息不存在，添加评论失败");
        }
        discusszhaopinxinxi.setD_id(null);
        discusszhaopinxinxi.setAddtime(TimeUtils.getTime());
        discusszhaopinxinxi.setUserid(yonghuId);
        discusszhaopinxinxiMapper.insert(discusszhaopinxinxi);
        return new Result(Code.SAVE_OK,discusszhaopinxinxi.getContent(),"发布招聘信息评论成功");
    }

    @Override
    public Result updateSelf(Discusszhaopinxinxi discusszhaopinxinxi, BigInteger yonghuId, BigInteger id) {
        //修改自己已经评论过的评论
        LambdaUpdateWrapper<Discusszhaopinxinxi> uw = new LambdaUpdateWrapper<>();
        uw.eq(Discusszhaopinxinxi::getUserid,yonghuId);
        uw.eq(Discusszhaopinxinxi::getRefid,discusszhaopinxinxi.getRefid());
        uw.eq(Discusszhaopinxinxi::getD_id,id);
        int update = discusszhaopinxinxiMapper.update(discusszhaopinxinxi, uw);
        if (update==0){ //不是自己的评论 or 未对该招聘信息评论
           return new Result(Code.UPDATE_ERR,"修改评论失败");
        }
        return new Result(Code.UPDATE_OK,discusszhaopinxinxi.getContent(),"修改求职者评论成功");

    }

    @Override
    public Result selectSelf(BigInteger yonghuId) {
        //查看自己的评论
        List<Zhaopinxinxi> zhaopinxinxiList = discusszhaopinxinxiMapper.selectSelf(yonghuId);
        if (zhaopinxinxiList.isEmpty()){
            return new Result(Code.GET_ERR,"你还没有评论");
        }
        return new Result(Code.GET_OK,zhaopinxinxiList,"查看自己招聘信息评论成功");
    }

    @Override
    public Result select(BigInteger r_id) {
        List<Zhaopinxinxi> zhaopinxinxiList =discusszhaopinxinxiMapper.select(r_id);
        if (zhaopinxinxiList.isEmpty()){
            return new Result(Code.GET_ERR,"还没有评论");
        }
        return new Result(Code.GET_OK,zhaopinxinxiList,"查看招聘信息评论成功");
    }

    @Override
    public Result delete(BigInteger id, BigInteger yonghuId) {
        if (!check(id, yonghuId)){
            return new Result(Code.DELETE_ERR,"删除招聘信息评论失败");
        }
        int i = discusszhaopinxinxiMapper.deleteById(id);
        if (i==0){
            return new Result(Code.DELETE_ERR,"删除招聘信息评论失败");
        }
       return new Result(Code.DELETE_OK,"删除招聘信息评论成功");
    }


    public Boolean check(BigInteger id, BigInteger yonghuId){
        Zhaopinxinxi zhaopinxinxi=discusszhaopinxinxiMapper.check(id,yonghuId);
        return Objects.nonNull(zhaopinxinxi);
    }
}
