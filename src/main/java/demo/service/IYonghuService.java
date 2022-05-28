package demo.service;

import demo.domain.Yonghu;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.util.Result;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IYonghuService extends IService<Yonghu> {

    Result login(String yonghuming,String mima);

    //获取角色信息
    List<String> getRole(BigInteger id);
    List<String> getPermission(BigInteger id);

    Yonghu findByYonghuming(String yonghuming);

    Result logout();

    Result register(Yonghu yonghu);

    Result selectAllYonghu();

    Result selectSelf(BigInteger yonghuId);


    Result updateSelf(Yonghu yonghu, Yonghu loginYonghu);

    Result delete(BigInteger id, BigInteger yonghuId);

    Result setAdmin(BigInteger id);

    Result selectWithYonghuming(String yonghuming);
}
