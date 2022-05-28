package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import demo.domain.Storeup;
import demo.mapper.StoreupMapper;
import demo.service.IStoreupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import org.apache.tomcat.jni.BIOCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class StoreupServiceImpl extends ServiceImpl<StoreupMapper, Storeup> implements IStoreupService {
    @Autowired
    private StoreupMapper storeupMapper;

    @Override
    public Result add(Storeup storeup, BigInteger yonghuId) {
        storeup.setAddtime(TimeUtils.getTime());
        storeup.setUserid(yonghuId);
        storeupMapper.insert(storeup);
        return new Result(Code.SAVE_OK,storeup,"添加收藏成功");
    }

    @Override
    public Result updateSelf(Storeup storeup, BigInteger yonghuID) {
        //判断是否为自己已经收藏过的新闻
        LambdaUpdateWrapper<Storeup> uw = new LambdaUpdateWrapper<>();
        uw.eq(Storeup::getUserid,yonghuID);
        uw.eq(Storeup::getRefid,storeup.getRefid());
        int update = storeupMapper.update(storeup, uw);
        if (update==0){
            return new Result(Code.UPDATE_ERR,"修改收藏失败");
        }
        return new Result(Code.UPDATE_OK,storeup,"修改收藏成功");
    }

    @Override
    public Result selectSelf(BigInteger yonghuId) {
        List<Storeup> storeupList=storeupMapper.selectSelf(yonghuId);
        if (storeupList.isEmpty()){
            return new Result(Code.GET_ERR,"你还没有收藏");
        }
        return new Result(Code.GET_OK,storeupList,"查看收藏成功");
    }

    @Override
    public Result delete(BigInteger id, BigInteger yonghuId) {
        if (!check(id,yonghuId)){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        int i = storeupMapper.deleteById(id);
        if (i==0){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        return new Result(Code.DELETE_OK,"删除收藏成功");
    }

    public Boolean check(BigInteger id, BigInteger yonghuId){
        Storeup storeup= storeupMapper.check(id,yonghuId);
        return Objects.nonNull(storeup);
    }
}
