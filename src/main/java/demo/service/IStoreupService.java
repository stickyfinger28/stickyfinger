package demo.service;

import demo.domain.Storeup;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IStoreupService extends IService<Storeup> {



    Result add(Storeup storeup, BigInteger yonghuId);

    Result updateSelf(Storeup storeup, BigInteger yonghuId);

    Result selectSelf(BigInteger yonghuId);


    Result delete(BigInteger id, BigInteger yonghuId);
}
