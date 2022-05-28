package demo.mapper;

import demo.domain.Yonghu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface YonghuMapper extends BaseMapper<Yonghu> {


   Yonghu findByYonghuming(String yonghuming);



   List<String> getRole(BigInteger id);

    void registerRole(BigInteger yonghuId);

    Yonghu selectSelf(BigInteger yonghuId);

    List<Yonghu> selectAll();

    List<String> getPermission(BigInteger id);

    void setAdmin(BigInteger id);

    List<Yonghu> selectWithYonghuming(String yonghuming);
}
