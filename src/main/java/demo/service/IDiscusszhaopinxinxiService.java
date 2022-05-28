package demo.service;

import demo.domain.Discusszhaopinxinxi;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 招聘信息评论表 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IDiscusszhaopinxinxiService extends IService<Discusszhaopinxinxi> {

    Result add(Discusszhaopinxinxi discusszhaopinxinxi, BigInteger yonghuId);

    Result updateSelf(Discusszhaopinxinxi discusszhaopinxinxi, BigInteger yonghuId, BigInteger id);

    Result selectSelf(BigInteger yonghuId);

    Result select(BigInteger r_id);

    Result delete(BigInteger id, BigInteger yonghuId);

}
