package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import demo.domain.News;
import demo.mapper.NewsMapper;
import demo.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 新闻资讯 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public Result add(News news) {
        news.setId(null);
        news.setAddtime(TimeUtils.getTime());
        newsMapper.insert(news);
        return new Result(Code.SAVE_OK,news,"添加新闻成功");
    }

    @Override
    public Result updateSelf(News news, BigInteger id) {

        News n = newsMapper.selectById(id);
        if (Objects.isNull(n)){ // 新闻不存在
            return new Result(Code.UPDATE_ERR,"修改新闻失败");
        }
        news.setId(id);
        newsMapper.updateById(news);
        return new Result(Code.UPDATE_OK,news,"修改新闻成功");
    }

    @Override
    public Result select() {
        QueryWrapper<News> qw = new QueryWrapper<>();

        List<News> newsList = newsMapper.selectList(qw);

        return new Result(Code.GET_OK,newsList,"查看新闻成功");
    }


    @Override
    public Result delete(BigInteger id) {
        int i = newsMapper.deleteById(id);
        if (i==0){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        return new Result(Code.DELETE_OK,"删除新闻资讯成功");
    }
}
