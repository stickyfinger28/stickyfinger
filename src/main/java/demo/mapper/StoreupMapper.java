package demo.mapper;

import demo.domain.Storeup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface StoreupMapper extends BaseMapper<Storeup> {

    List<Storeup> selectSelf(BigInteger yonghuId);

    Storeup check(BigInteger id, BigInteger yonghuId);
}
