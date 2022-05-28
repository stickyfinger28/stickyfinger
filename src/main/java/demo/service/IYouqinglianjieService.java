package demo.service;

import demo.domain.Youqinglianjie;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 友情链接 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IYouqinglianjieService extends IService<Youqinglianjie> {

    Result add(Youqinglianjie youqinglianjie);

    Result delete(BigInteger id);

    Result updateSelf(Youqinglianjie youqinglianjie, BigInteger id);

    Result select();
}
