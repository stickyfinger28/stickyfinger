package demo.mapper;

import demo.domain.L.LQiyexinxi;
import demo.domain.L.LYingpinxinxi;
import demo.domain.L.SYingpinxinxi;
import demo.domain.Yingpinxinxi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import demo.domain.Yonghu;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 应聘信息 Mapper 接口
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface YingpinxinxiMapper extends BaseMapper<Yingpinxinxi> {

    List<Yingpinxinxi> select(BigInteger id);

    Yingpinxinxi check(BigInteger id, String yonghuming);

    void updateByQiyexinxi(LQiyexinxi lQiyexinxi);

    void updateByYonghu(Yonghu yonghu);

    void updateSelf(LYingpinxinxi lYingpinxinxi);

    Yingpinxinxi checkSH(BigInteger id, BigInteger yonghuId);

    void updateBySH(SYingpinxinxi syingpinxinxi);
}
