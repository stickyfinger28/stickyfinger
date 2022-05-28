package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import demo.domain.Youqinglianjie;
import demo.mapper.YingpinxinxiMapper;
import demo.mapper.YouqinglianjieMapper;
import demo.service.IYouqinglianjieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 友情链接 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class YouqinglianjieServiceImpl extends ServiceImpl<YouqinglianjieMapper, Youqinglianjie> implements IYouqinglianjieService {


    @Autowired
    private YouqinglianjieMapper youqinglianjieMapper;

    @Override
    public Result add(Youqinglianjie youqinglianjie) {
        youqinglianjie.setAddtime(TimeUtils.getTime());
        int insert = youqinglianjieMapper.insert(youqinglianjie);
        if (insert==0){
            return new Result(Code.SAVE_ERR,"上传友情链接失败");
        }
        return new Result(Code.SAVE_OK,"上传友情链接成功");
    }

    @Override
    public Result delete(BigInteger id) {
        int i = youqinglianjieMapper.deleteById(id);
        if(i==0){
            return new Result(Code.DELETE_ERR,"删除友情链接失败");
        }
        return new Result(Code.DELETE_ERR,"删除友情链接成功");
    }

    @Override
    public Result updateSelf(Youqinglianjie youqinglianjie, BigInteger id) {
        youqinglianjie.setId(id);
        int i = youqinglianjieMapper.updateById(youqinglianjie);
        if (i==0){
            return new Result(Code.UPDATE_ERR,"修改友情链接失败");
        }
        return new Result(Code.UPDATE_OK,"修改友情链接成功");
    }

    @Override
    public Result select() {
        QueryWrapper<Youqinglianjie> qw = new QueryWrapper<>();
        List<Youqinglianjie> youqinglianjieList = youqinglianjieMapper.selectList(qw);
        if (youqinglianjieList.isEmpty()){
            return new Result(Code.GET_OK,"无友情链接");
        }
        return new Result(Code.GET_OK,youqinglianjieList,"查看友情链接成功");
    }
}
