package demo.service;

import demo.domain.L.LYingpinxinxi;
import demo.domain.L.SYingpinxinxi;
import demo.domain.Yingpinxinxi;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.domain.Yonghu;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 应聘信息 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IYingpinxinxiService extends IService<Yingpinxinxi> {

    Result add(Yingpinxinxi yingpinxinxi, Yonghu yonghu);

    Result selectSelf(Yonghu yonghu);

    Result select(Yonghu yonghu);

    Result updateSh(SYingpinxinxi syingpinxinxi, Yonghu boss);

    Result delete(BigInteger id, Yonghu yonghu);

    Result updateSelf(LYingpinxinxi lYingpinxinxi, Yonghu yonghu);

}
