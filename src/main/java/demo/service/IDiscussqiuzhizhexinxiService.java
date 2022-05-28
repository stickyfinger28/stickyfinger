package demo.service;

import demo.domain.Discussqiuzhizhexinxi;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 求职者信息评论表 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IDiscussqiuzhizhexinxiService extends IService<Discussqiuzhizhexinxi> {

    Result add(Discussqiuzhizhexinxi discussqiuzhizhexinxi, BigInteger yonghuId);

    Result deleteSelf(BigInteger id, BigInteger yonghuId);

    Result updateSelf(Discussqiuzhizhexinxi discussqiuzhizhexinxi, BigInteger yonghuId, BigInteger id);


    Result selectSelf(BigInteger yonghuId);

    Result select(BigInteger r_id);


}
