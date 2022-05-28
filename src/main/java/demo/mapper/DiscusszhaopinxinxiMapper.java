package demo.mapper;

import demo.domain.Discusszhaopinxinxi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import demo.domain.Zhaopinxinxi;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 招聘信息评论表 Mapper 接口
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface DiscusszhaopinxinxiMapper extends BaseMapper<Discusszhaopinxinxi> {

    List<Zhaopinxinxi> selectSelf(BigInteger yonghuId);

    List<Zhaopinxinxi> select(BigInteger r_id);

    Zhaopinxinxi check(BigInteger id, BigInteger yonghuId);
}
