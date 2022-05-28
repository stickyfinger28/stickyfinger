package demo.mapper;

import demo.domain.L.LQiuzhizhexinxi;
import demo.domain.Qiuzhizhexinxi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import demo.domain.Yonghu;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 求职者信息 Mapper 接口
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface QiuzhizhexinxiMapper extends BaseMapper<Qiuzhizhexinxi> {

    List<Qiuzhizhexinxi> selectSelf(String yonghuming);

    void updateByYonghu(Yonghu yonghu);

    Qiuzhizhexinxi check(BigInteger id, String yonghuming);

    void updateSelf(LQiuzhizhexinxi lQiuzhizhexinxi);

    void setStaff(BigInteger id);

    void dianzhan(Integer id, int i);

    void diancai(Integer id, int i);
}
