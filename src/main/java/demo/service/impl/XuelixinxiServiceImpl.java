package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import demo.domain.Qiuzhizhexinxi;
import demo.domain.Xuelixinxi;
import demo.domain.Yonghu;
import demo.mapper.QiuzhizhexinxiMapper;
import demo.mapper.XuelixinxiMapper;
import demo.service.IQiuzhizhexinxiService;
import demo.service.IXuelixinxiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;

/**
 * <p>
 * 学历信息 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class XuelixinxiServiceImpl extends ServiceImpl<XuelixinxiMapper, Xuelixinxi> implements IXuelixinxiService {

    @Autowired
    private XuelixinxiMapper xuelixinxiMapper;

    @Autowired
    private IQiuzhizhexinxiService qiuzhizhexinxiService;

    @Autowired
    private QiuzhizhexinxiMapper qiuzhizhexinxiMapper;

    @Override
    public Result add(Xuelixinxi xuelixinxi, BigInteger yonghuId) {
        Xuelixinxi x = xuelixinxiMapper.selectById(yonghuId);
        if (Objects.nonNull(x)){
            return new Result(Code.SAVE_ERR,x,"你已添加学历信息");
        }
        xuelixinxi.setAddtime(TimeUtils.getTime());
        xuelixinxi.setId(yonghuId);
        xuelixinxiMapper.insert(xuelixinxi);
        return new Result(Code.SAVE_OK,xuelixinxi,"添加学历信息成功");
    }

    @Override
    public Result updateSelf(Xuelixinxi xuelixinxi, Yonghu yonghu) {
        LambdaUpdateWrapper<Xuelixinxi> uw = new LambdaUpdateWrapper<>();
        uw.eq(Xuelixinxi::getId,yonghu.getId());
        int update = xuelixinxiMapper.update(xuelixinxi, uw);
        if(update==0){
            return new Result(Code.UPDATE_ERR,"修改学历信息失败");
        }

        //如果存在求职者信息，需要更改对应的学历信息
        if (Objects.nonNull(qiuzhizhexinxiService.selectSelf(yonghu).getData())){
            LambdaUpdateWrapper<Qiuzhizhexinxi> quw = new LambdaUpdateWrapper<>();
            quw.eq(Qiuzhizhexinxi::getYonghuming,yonghu.getYonghuming());
            quw.set(Qiuzhizhexinxi::getXueli,xuelixinxi.getXueli());
            qiuzhizhexinxiMapper.update(null,quw);
        }

        return new Result(Code.UPDATE_OK,xuelixinxi,"修改学历信息成功");
    }

    @Override
    public Result delete(BigInteger yonghuId) {
        int i = xuelixinxiMapper.deleteById(yonghuId);
        if (i==0){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        return new Result(Code.DELETE_OK,"删除学历信息成功");
    }
}
