package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import demo.domain.Gangweifenlei;
import demo.mapper.GangweifenleiMapper;
import demo.service.IGangweifenleiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import demo.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 岗位分类 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class GangweifenleiServiceImpl extends ServiceImpl<GangweifenleiMapper, Gangweifenlei> implements IGangweifenleiService {

    @Autowired
    private IGangweifenleiService gangweifenleiService;

    @Autowired
    private GangweifenleiMapper gangweifenleiMapper;
    @Override
    public Result saveByList(List<Gangweifenlei> gangweifenleiList) {

        ArrayList<Gangweifenlei> list = new ArrayList<>();
        for (Gangweifenlei gangweifenlei : gangweifenleiList) {
            LambdaQueryWrapper<Gangweifenlei> qw = new LambdaQueryWrapper<>();
            qw.eq(Gangweifenlei::getGangweileibie,gangweifenlei.getGangweileibie());
            if (Objects.isNull(gangweifenleiMapper.selectOne(qw))){
                gangweifenlei.setId(null);
                gangweifenlei.setAddtime(TimeUtils.getTime());
                list.add(gangweifenlei);
            }
        }
        if(list.isEmpty()){
            return new Result(Code.SAVE_ERR,"重复添加");
        }
        gangweifenleiService.saveBatch(list);
        return new Result(Code.SAVE_OK,list,"添加成功");
    }

    @Override
    public Result update(Gangweifenlei gangweifenlei, BigInteger id) {
        LambdaUpdateWrapper<Gangweifenlei> uw = new LambdaUpdateWrapper<>();
        uw.eq(Gangweifenlei::getId,id);
        gangweifenleiMapper.update(gangweifenlei,uw);

        return new Result(Code.UPDATE_OK,"修改为"+gangweifenlei.getGangweileibie()+"成功");
    }

    @Override
    public Result select() {
        QueryWrapper<Gangweifenlei> qw = new QueryWrapper<>();
        List<Gangweifenlei> list = gangweifenleiMapper.selectList(qw);
        return new Result(Code.GET_OK,list,"查询岗位分类成功");
    }

    @Override
    public Result updateSelf(Gangweifenlei gangweifenlei) {
        String gangweileibie = gangweifenlei.getGangweileibie();
        LambdaQueryWrapper<Gangweifenlei> qw = new LambdaQueryWrapper<>();
        qw.eq(Gangweifenlei::getGangweileibie,gangweileibie);
        if (Objects.nonNull(gangweifenleiMapper.selectOne(qw))){
            return new Result(Code.UPDATE_ERR,"岗位分类已存在");
        }
        int i = gangweifenleiMapper.updateById(gangweifenlei);
        if (i==0){
            return new Result(Code.UPDATE_ERR,"修改岗位分类失败");
        }
        return new Result(Code.UPDATE_OK,"修改岗位分类成功");
    }
}

