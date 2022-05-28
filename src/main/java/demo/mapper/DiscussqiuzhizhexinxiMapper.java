package demo.mapper;

import demo.domain.Discussqiuzhizhexinxi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import demo.domain.Qiuzhizhexinxi;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 求职者信息评论表 Mapper 接口
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface DiscussqiuzhizhexinxiMapper extends BaseMapper<Discussqiuzhizhexinxi> {

    List<Qiuzhizhexinxi> selectSelf(BigInteger yonghuId);

    List<Qiuzhizhexinxi> select(BigInteger r_id);

    Qiuzhizhexinxi check(BigInteger id, BigInteger yonghuId);
}
