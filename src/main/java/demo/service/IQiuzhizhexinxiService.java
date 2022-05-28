package demo.service;

import demo.domain.L.LQiuzhizhexinxi;
import demo.domain.Qiuzhizhexinxi;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.domain.Yonghu;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 求职者信息 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IQiuzhizhexinxiService extends IService<Qiuzhizhexinxi> {

    Result add(Qiuzhizhexinxi qiuzhizhexinxi, Yonghu yonghu);

    Result selectSelf(Yonghu yonghu);


    Result selectAll();

    Result dianzhan(Integer id, BigInteger yonghuId);

    Result diancai(Integer id, BigInteger yonghuId);

    Result delete(BigInteger id, Yonghu yonghu);


    Result updateSelf(LQiuzhizhexinxi lqiuzhizhexinxi, Yonghu yonghu);
}
