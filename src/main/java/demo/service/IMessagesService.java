package demo.service;

import demo.domain.Messages;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.util.Result;
import org.apache.tomcat.jni.BIOCallback;

import java.math.BigInteger;

/**
 * <p>
 * 留言板 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface IMessagesService extends IService<Messages> {

    Result add(Messages messages, BigInteger yonghuId);

    Result updateSelf(Messages messages, BigInteger yonghuId);

    Result selectSelf(BigInteger yonghuId);

    Result delete(BigInteger id, BigInteger yonghuId);
}
