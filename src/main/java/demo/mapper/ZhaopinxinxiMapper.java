package demo.mapper;

import demo.domain.L.LQiyexinxi;
import demo.domain.L.LZhaopinxinxi;
import demo.domain.Zhaopinxinxi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 招聘信息 Mapper 接口
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface ZhaopinxinxiMapper extends BaseMapper<Zhaopinxinxi> {

    List<Zhaopinxinxi> select();

    List<Zhaopinxinxi> selectSelf(BigInteger bossId);

    Zhaopinxinxi check(BigInteger zhaopinxinxiId, BigInteger yonghuId);

    void updateByQiyexinxi(LQiyexinxi lQiyexinxi);

    void updateSelf(LZhaopinxinxi lZhaopinxinxi);

    void diancai(Integer id, int i);

    void dianzhan(Integer id, int i);
}
