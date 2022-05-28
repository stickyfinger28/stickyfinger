package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import demo.domain.L.LQiyexinxi;
import demo.domain.Qiyexinxi;
import demo.domain.Yonghu;
import demo.mapper.QiyexinxiMapper;
import demo.mapper.YingpinxinxiMapper;
import demo.mapper.ZhaopinxinxiMapper;
import demo.service.IQiyexinxiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 企业信息 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class QiyexinxiServiceImpl extends ServiceImpl<QiyexinxiMapper, Qiyexinxi> implements IQiyexinxiService {
    @Autowired
    private QiyexinxiMapper qiyexinxiMapper;

    @Autowired
    private ZhaopinxinxiMapper zhaopinxinxiMapper;

    @Autowired
    private YingpinxinxiMapper yingpinxinxiMapper;

    @Override
    public Result add(Qiyexinxi qiyexinxi, Yonghu boss) {


        //检查企业编号是否重复
        LambdaQueryWrapper<Qiyexinxi> qw = new LambdaQueryWrapper<>();
        qw.eq(Qiyexinxi::getQiyebianhao,qiyexinxi.getQiyebianhao());
        if (Objects.nonNull(qiyexinxiMapper.selectOne(qw))){
            return new Result(Code.SAVE_ERR,"该企业信息已存在，请勿重复添加");
        }
        //赋予boss角色
        qiyexinxiMapper.setBoss(boss.getId());
        //设置默认企业信息
        qiyexinxi.setAddtime(TimeUtils.getTime());
        qiyexinxi.setUserid(boss.getId());
        //加密密码
        qiyexinxi.setMima(PassWordUtil.encode(qiyexinxi.getMima()));

        qiyexinxiMapper.insert(qiyexinxi);
        return new Result(Code.SAVE_OK,qiyexinxi,"添加企业信息成功");

    }

    @Override //检查编号密码
    public Result delete(BigInteger id, BigInteger yonghuId) {
        if (!check(id, yonghuId)){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        int i = qiyexinxiMapper.deleteById(id);
        if (i==0){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        return new Result(Code.DELETE_OK,"删除企业信息成功");
    }

    @Override //修改企业信息的编号后，对应的招聘信息改变
    public Result updateSelf(Yonghu yonghu, LQiyexinxi lQiyexinxi) {
        //检验企业编号与密码
        Boolean qp = checkQP(lQiyexinxi,yonghu);
        if (!qp){
            return new Result(Code.UPDATE_ERR,"企业信息验证失败");
        }
        //检查更正的企业编号是否重复
        if (Objects.nonNull(lQiyexinxi.getQiyebianhao())){
            LambdaQueryWrapper<Qiyexinxi> qw2 = new LambdaQueryWrapper<>();
            qw2.eq(Qiyexinxi::getQiyebianhao,lQiyexinxi.getQiyebianhao());
            if (Objects.nonNull(qiyexinxiMapper.selectOne(qw2))){
                return new Result(Code.UPDATE_ERR,"该企业编号已存在，请重新输入企业编号");
            }
        }else {
            lQiyexinxi.setQiyebianhao(lQiyexinxi.getUqiyebianhao());
        }
        //设置密码
        if (Objects.nonNull(lQiyexinxi.getMima())){
            lQiyexinxi.setMima(PassWordUtil.encode(lQiyexinxi.getMima()));
        }
        qiyexinxiMapper.updateSelf(lQiyexinxi);
        try {
            //对应的招聘信息改变
            zhaopinxinxiMapper.updateByQiyexinxi(lQiyexinxi);
            //对应的应聘信息改变
            yingpinxinxiMapper.updateByQiyexinxi(lQiyexinxi);
        } catch (Exception e) {
            return new Result(Code.UPDATE_OK,"修改企业信息成功");
        }
        return new Result(Code.UPDATE_OK,"修改企业信息成功");
    }

    @Override
    public Result select() {
        List<Qiyexinxi> list = qiyexinxiMapper.selectAll();
        return new Result(Code.GET_OK,list,"查询企业信息成功");
    }

    @Override
    public Result selectSelf(Yonghu boss) {
        LambdaQueryWrapper<Qiyexinxi> qw = new LambdaQueryWrapper<>();
        qw.eq(Qiyexinxi::getUserid,boss.getId());
        List<Qiyexinxi> qiyexinxiList = qiyexinxiMapper.selectList(qw);
        if (qiyexinxiList.size()==0){
            return new Result(Code.GET_ERR,"你还未添加企业信息");
        }
        return new Result(Code.GET_OK,qiyexinxiList,"查询个人企业信息成功");
    }

    public Boolean check(BigInteger id, BigInteger yonghuId){
        LambdaQueryWrapper<Qiyexinxi> qw = new LambdaQueryWrapper<>();
        qw.eq(Qiyexinxi::getUserid,yonghuId);
        qw.eq(Qiyexinxi::getId,id);
        Qiyexinxi qiyexinxi = qiyexinxiMapper.selectOne(qw);
        return Objects.nonNull(qiyexinxi);
    }

    public Boolean checkQP(LQiyexinxi lQiyexinxi,Yonghu yonghu){
        //检验企业编号与密码
        LambdaQueryWrapper<Qiyexinxi> qw1 = new LambdaQueryWrapper<>();
        qw1.eq(Qiyexinxi::getQiyebianhao,lQiyexinxi.getUqiyebianhao());
        qw1.eq(Qiyexinxi::getUserid,yonghu.getId());
        Qiyexinxi q = qiyexinxiMapper.selectOne(qw1);
        if (Objects.isNull(q)){
            return false;
        }
        return PassWordUtil.matches(lQiyexinxi.getUmima(), q.getMima());
    }


}
