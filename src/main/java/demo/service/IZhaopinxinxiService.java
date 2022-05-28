package demo.service;

import demo.domain.L.LZhaopinxinxi;
import demo.domain.Yonghu;
import demo.domain.Zhaopinxinxi;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 招聘信息 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IZhaopinxinxiService extends IService<Zhaopinxinxi> {

    Result add(Zhaopinxinxi zhaopinxinxi, Yonghu boss);

    Result delete(BigInteger id, BigInteger yonghuId);

    Result updateSelf(LZhaopinxinxi lZhaopinxinxi,Yonghu yonghu);

    Result select();

    Result selectSelf(Yonghu boss);

    Result dianzhan(Integer id, BigInteger yonghuId);

    Result diancai(Integer id, BigInteger yonghuId);
}
