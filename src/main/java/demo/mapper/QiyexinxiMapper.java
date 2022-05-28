package demo.mapper;

import demo.domain.L.LQiyexinxi;
import demo.domain.Qiyexinxi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 企业信息 Mapper 接口
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface QiyexinxiMapper extends BaseMapper<Qiyexinxi> {

    List<Qiyexinxi> selectAll();


    void updateSelf(LQiyexinxi lQiyexinxi);

    void setBoss(BigInteger id);
}
