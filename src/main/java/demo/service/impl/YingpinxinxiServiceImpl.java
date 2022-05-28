package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import demo.domain.L.LYingpinxinxi;
import demo.domain.L.SYingpinxinxi;
import demo.domain.Qiyexinxi;
import demo.domain.Yingpinxinxi;
import demo.domain.Yonghu;
import demo.domain.Zhaopinxinxi;
import demo.mapper.QiyexinxiMapper;
import demo.mapper.YingpinxinxiMapper;
import demo.mapper.ZhaopinxinxiMapper;
import demo.service.IYingpinxinxiService;
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
 * 应聘信息 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class YingpinxinxiServiceImpl extends ServiceImpl<YingpinxinxiMapper, Yingpinxinxi> implements IYingpinxinxiService {

    @Autowired
    private QiyexinxiMapper qiyexinxiMapper;
    @Autowired
    private ZhaopinxinxiMapper zhaopinxinxiMapper;

    @Autowired
    private YingpinxinxiMapper yingpinxinxiMapper;


    @Override
    public Result add(Yingpinxinxi yingpinxinxi, Yonghu yonghu) {
        // 判断是否存在该公司
        LambdaQueryWrapper<Qiyexinxi> qyqw = new LambdaQueryWrapper<>();
        qyqw.eq(Qiyexinxi::getQiyebianhao, yingpinxinxi.getQiyebianhao());
        Qiyexinxi qiyexinxi = qiyexinxiMapper.selectOne(qyqw);
            //企业不存在
        if (Objects.isNull(qiyexinxi)) {
            return new Result(Code.SAVE_ERR, "企业编号有误，请检查");
        }
        // 判断应聘的岗位是否为该公式招聘的岗位
        LambdaQueryWrapper<Zhaopinxinxi> zpqw = new LambdaQueryWrapper<>();
        zpqw.eq(Zhaopinxinxi::getQiyebianhao, qiyexinxi.getQiyebianhao());
        zpqw.eq(Zhaopinxinxi::getZhaopingangwei, yingpinxinxi.getZhaopingangwei());
        Zhaopinxinxi zhaopinxinxi = zhaopinxinxiMapper.selectOne(zpqw);
            //招聘岗位不存在
        if (Objects.isNull(zhaopinxinxi)) {
            return new Result(Code.SAVE_ERR, "应聘岗位信息有误，请检查");
        }

        // 设置应聘信息默认企业信息
        yingpinxinxi.setQiyemingcheng(qiyexinxi.getQiyemingcheng());
        yingpinxinxi.setFuzeren(qiyexinxi.getFuzeren());
        yingpinxinxi.setLianxidianhua(qiyexinxi.getLianxidianhua());
        // 设置应聘信息默认其他信息
        yingpinxinxi.setYonghuming(yonghu.getYonghuming());
        yingpinxinxi.setAddtime(TimeUtils.getTime());
        yingpinxinxi.setDianhua(yonghu.getDianhua());
        yingpinxinxi.setXingming(yonghu.getXingming());
        //检查是否重复添加对应公司岗位的应聘信息
        LambdaQueryWrapper<Yingpinxinxi> ypqw = new LambdaQueryWrapper<>();
        ypqw.eq(Yingpinxinxi::getQiyebianhao, yingpinxinxi.getQiyebianhao());
        ypqw.eq(Yingpinxinxi::getYonghuming, yonghu.getYonghuming());
        ypqw.eq(Yingpinxinxi::getZhaopingangwei, yingpinxinxi.getZhaopingangwei());
            //已经添加过该公司相应招聘岗位
        if (Objects.nonNull(yingpinxinxiMapper.selectOne(ypqw))) {
            return new Result(Code.SAVE_ERR, "你已添加过该公司招聘岗位的应聘信息，请勿重复申请");
        } else {
            yingpinxinxiMapper.insert(yingpinxinxi);
            return new Result(Code.SAVE_OK, yingpinxinxi, "添加应聘信息成功");
        }

    }

    @Override
    public Result selectSelf(Yonghu yonghu) {
        LambdaQueryWrapper<Yingpinxinxi> qw = new LambdaQueryWrapper<>();
        qw.eq(Yingpinxinxi::getYonghuming,yonghu.getYonghuming());
        List<Yingpinxinxi> yingpinxinxiList = yingpinxinxiMapper.selectList(qw);
        if (yingpinxinxiList.isEmpty()){
            return new Result(Code.GET_ERR,"你还未添加任何应聘信息");
        }
        return new Result(Code.GET_OK,yingpinxinxiList,"查看个人应聘岗位成功");
    }

    @Override
    public Result select(Yonghu yonghu) {
        BigInteger id = yonghu.getId();
        List<Yingpinxinxi> yingpinxinxiList=yingpinxinxiMapper.select(id);
        if (yingpinxinxiList.isEmpty()){
            return new Result(Code.GET_ERR,"应聘信息为空");
        }
        return new Result(Code.GET_OK,yingpinxinxiList,"查看来应聘的信息成功 ");
    }

    @Override //审核应聘信息
    public Result updateSh(SYingpinxinxi syingpinxinxi, Yonghu boss) {
        Yingpinxinxi yingpinxinxi=yingpinxinxiMapper.checkSH(syingpinxinxi.getId(),boss.getId());
       if (Objects.isNull(yingpinxinxi)){
           return new Result(Code.UPDATE_ERR,"应聘信息验证失败");
       }
        yingpinxinxiMapper.updateBySH(syingpinxinxi);
        return new Result(Code.UPDATE_OK,"审核回复应聘信息成功");
    }

    @Override
    public Result updateSelf(LYingpinxinxi lYingpinxinxi, Yonghu yonghu) {
        Qiyexinxi qiyexinxi = new Qiyexinxi();
        if (Objects.nonNull(lYingpinxinxi.getQiyebianhao())){
            // 判断是否存在该公司
            LambdaQueryWrapper<Qiyexinxi> qyqw = new LambdaQueryWrapper<>();
            qyqw.eq(Qiyexinxi::getQiyebianhao, lYingpinxinxi.getQiyebianhao());
            qiyexinxi = qiyexinxiMapper.selectOne(qyqw);
            //企业不存在
            if (Objects.isNull(qiyexinxi)) {
                return new Result(Code.SAVE_ERR, "企业编号有误，请检查");
            }
        }
        if (Objects.nonNull(lYingpinxinxi.getZhaopingangwei())){
            // 判断应聘的岗位是否为该公式招聘的岗位
            LambdaQueryWrapper<Zhaopinxinxi> zpqw = new LambdaQueryWrapper<>();
            zpqw.eq(Zhaopinxinxi::getQiyebianhao, qiyexinxi.getQiyebianhao());
            zpqw.eq(Zhaopinxinxi::getZhaopingangwei, lYingpinxinxi.getZhaopingangwei());
            Zhaopinxinxi zhaopinxinxi = zhaopinxinxiMapper.selectOne(zpqw);
            //招聘岗位不存在
            if (Objects.isNull(zhaopinxinxi)) {
                return new Result(Code.SAVE_ERR, "应聘岗位信息有误，请检查");
            }
        }
        if (!check(lYingpinxinxi.getId(),yonghu)){
            return new Result(Code.UPDATE_ERR,"应聘信息验证失败");
        }
        yingpinxinxiMapper.updateSelf(lYingpinxinxi);
        return new Result(Code.UPDATE_OK,"修改应聘信息成功");
    }

    @Override
    public Result delete(BigInteger id, Yonghu yonghu) {
        if (!check(id, yonghu)){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        int i = yingpinxinxiMapper.deleteById(id);
        if (i==0){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        return new Result(Code.DELETE_OK,"删除应聘信息成功");
    }



    public Boolean check(BigInteger id,Yonghu yonghu){
        String yonghuming = yonghu.getYonghuming();
        Yingpinxinxi yingpinxinxi=yingpinxinxiMapper.check(id,yonghuming);
        return Objects.nonNull(yingpinxinxi);
    }
}
