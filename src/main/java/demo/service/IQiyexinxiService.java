package demo.service;

import demo.domain.L.LQiyexinxi;
import demo.domain.Qiyexinxi;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.domain.Yonghu;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 企业信息 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IQiyexinxiService extends IService<Qiyexinxi> {

    Result add(Qiyexinxi qiyexinxi, Yonghu boss);

    Result delete(BigInteger id, BigInteger yonghuId);

    Result updateSelf(Yonghu yonghu, LQiyexinxi lQiyexinxi);

    Result select();


    Result selectSelf(Yonghu boss);

}
