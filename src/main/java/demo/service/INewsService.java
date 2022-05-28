package demo.service;

import demo.domain.News;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.util.Result;

import java.math.BigInteger;

/**
 * <p>
 * 新闻资讯 服务类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
public interface INewsService extends IService<News> {

    Result add(News news);

    Result updateSelf(News news, BigInteger id);

    Result select();


    Result delete(BigInteger id);
}
