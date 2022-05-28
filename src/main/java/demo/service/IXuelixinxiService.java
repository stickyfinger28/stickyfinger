package demo.service;

import demo.domain.Xuelixinxi;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.domain.Yonghu;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 学历信息 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IXuelixinxiService extends IService<Xuelixinxi> {


    Result add(Xuelixinxi xuelixinxi, BigInteger yonghuId);

    Result updateSelf(Xuelixinxi xuelixinxi, Yonghu yonghuId);


    Result delete(BigInteger yonghuId);
}
