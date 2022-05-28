package demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import demo.mapper.QiuzhizhexinxiMapper;
import demo.mapper.YingpinxinxiMapper;
import demo.security.domain.LoginUser;
import demo.domain.Token;
import demo.domain.Yonghu;
import demo.mapper.YonghuMapper;
import demo.service.IQiuzhizhexinxiService;
import demo.service.ITokenService;
import demo.service.IYonghuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@Service
public class YonghuServiceImpl extends ServiceImpl<YonghuMapper, Yonghu> implements IYonghuService {



    @Autowired
    private QiuzhizhexinxiMapper qiuzhizhexinxiMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private YonghuMapper yonghuMapper;

    @Autowired
    private YingpinxinxiMapper yingpinxinxiMapper;

    @Override // 登入
    public Result login(String yonghuming, String mima) {
        // AuthenticationManager authenticate进行用户密码认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(yonghuming, mima);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 认证失败
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("登录失败");
        }
        // 认证成功
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 通过loginYonghu信息生成JWToken对象
        Yonghu loginYonghu = loginUser.getYonghu();
        Token token = JwtUtil.createToken(loginUser);
        // 判断另一端是否有登录
        if (loginYonghu.getState() == 1) {
            LambdaUpdateWrapper<Yonghu> uw = new LambdaUpdateWrapper<>();
            uw.eq(Yonghu::getId, loginYonghu.getId());
            uw.set(Yonghu::getState, 0);
            yonghuMapper.update(null, uw);
            return new Result(Code.Login_ERR, "你已经在另一处登录，请检查");
        }
        // 判断是否已经被删除
        if (loginYonghu.getDel() == 1) {
            return new Result(Code.Login_ERR, "该用户不存在");
        }
        // 将Token类存储到数据库中
        LambdaUpdateWrapper<Token> uw = new LambdaUpdateWrapper<>();
        uw.eq(Token::getUserid, loginYonghu.getId());
        tokenService.saveOrUpdate(token, uw);
        // 更改用户认证状态
        LambdaUpdateWrapper<Yonghu> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Yonghu::getId, loginYonghu.getId());
        wrapper.set(Yonghu::getState, 1);
        yonghuMapper.update(null, wrapper);
        return new Result(Code.Login_OK, token, "登陆成功");
    }

    @Override // 注册
    public Result register(Yonghu yonghu) {
        // 查询用户名是否重复
        LambdaQueryWrapper<Yonghu> qw1 = new LambdaQueryWrapper<>();
        qw1.eq(Yonghu::getYonghuming, yonghu.getYonghuming());
        if (Objects.nonNull(yonghuMapper.selectOne(qw1))) {
            return new Result(Code.SAVE_ERR, "用户名重复，请重新输入");
        }
        yonghu.setAddtime(TimeUtils.getTime());
        yonghu.setMima(PassWordUtil.encode(yonghu.getMima()));
        yonghuMapper.insert(yonghu);
        LambdaQueryWrapper<Yonghu> qw2 = new LambdaQueryWrapper<>();
        qw2.eq(Yonghu::getYonghuming, yonghu.getYonghuming());
        Yonghu one = yonghuMapper.selectOne(qw2);

        // 给新注册的用户visitor角色(4)
        yonghuMapper.registerRole(one.getId());
        return new Result(Code.SAVE_OK, "注册成功,请重新登录");
    }


    @Override //修改用户基本信息
    public Result updateSelf(Yonghu yonghu, Yonghu loginYonghu) {
        // 检查是否修改用户名
        // 修改用户名
        if (Objects.nonNull(yonghu.getYonghuming())) {
            LambdaQueryWrapper<Yonghu> qw = new LambdaQueryWrapper<>();
            qw.eq(Yonghu::getYonghuming, yonghu.getYonghuming());
            Yonghu y = yonghuMapper.selectOne(qw);
            if (Objects.nonNull(y)) {
                return new Result(Code.UPDATE_ERR, "用户名重复，请更改");
            }
            yonghu.setState(0);
        }else {
            yonghu.setYonghuming(loginYonghu.getYonghuming());
        }
        //修改密码
        if (Objects.nonNull(yonghu.getMima())){
            yonghu.setMima(PassWordUtil.encode(yonghu.getMima()));
            yonghu.setState(0);
        }
        LambdaUpdateWrapper<Yonghu> uw = new LambdaUpdateWrapper<>();
        uw.eq(Yonghu::getId, loginYonghu.getId());
        int update = yonghuMapper.update(yonghu, uw);
        try {
            //对应求职者信息改变
            qiuzhizhexinxiMapper.updateByYonghu(yonghu);
            //对应应聘信息改变
            yingpinxinxiMapper.updateByYonghu(yonghu);
        } catch (Exception e) {
            if (update == 0) {
                return new Result(Code.UPDATE_ERR, "修改基本信息失败");
            }
        }
        return new Result(Code.UPDATE_OK,"修改用户基本信息成功");
    }

    @Override //删除用户
    public Result delete(BigInteger id, BigInteger yonghuId) {
        if (!id.equals(yonghuId)) {
            return new Result(Code.DELETE_ERR, "注销用户失败");
        }
        // del
        int i = yonghuMapper.deleteById(id);
        if (i == 0) {
            return new Result(Code.DELETE_ERR, "注销用户失败");
        }
        return new Result(Code.DELETE_OK, "注销用户成功");
    }

    @Override
    public Result setAdmin(BigInteger id) {
        Yonghu yonghu = yonghuMapper.selectById(id);
        if (Objects.isNull(yonghu)){
            return new Result(Code.SAVE_ERR,"用户不存在");
        }
        yonghuMapper.setAdmin(id);
        return new Result(Code.SAVE_OK,"设置管理员成功");
    }

    @Override
    public Result selectWithYonghuming(String yonghuming) {
        List<Yonghu> yonghuList = yonghuMapper.selectWithYonghuming(yonghuming);
        if (yonghuList.isEmpty()){
            return new Result(Code.GET_OK,"暂无结果");
        }
        return new Result(Code.GET_OK,yonghuList,"查询成功");
    }

    @Override //查询个人信息
    public Result selectSelf(BigInteger yonghuId) {
        Yonghu yonghu = yonghuMapper.selectSelf(yonghuId);
        if (Objects.isNull(yonghu)) {
            return new Result(Code.GET_ERR, "查询个人信息失败");
        }
        return new Result(Code.GET_OK, yonghu, "查询个人信息成功");
    }

    @Override // 查询所有用户信息 需要admin的权限
    public Result selectAllYonghu() {
        List<Yonghu> yonghuList = yonghuMapper.selectAll();
        return new Result(Code.GET_OK, yonghuList, "查询成功");
    }


    @Override // 登出
    public Result logout() {
        // 获取SecurityContextHolder中的用户id
        Yonghu loginYonghu = WebUtils.getLoginUser().getYonghu();
        loginYonghu.setState(0);
        // 更改用户认证状态
        yonghuMapper.updateById(loginYonghu);
        return new Result(HttpStatus.OK.value(), "退出成功");
    }


    // 获取角色信息
    @Override
    public List<String> getRole(BigInteger id) {

        return yonghuMapper.getRole(id);
    }

    public List<String> getPermission(BigInteger id){
        return yonghuMapper.getPermission(id);
    }

    @Override
    public Yonghu findByYonghuming(String yonghuming) {
        return yonghuMapper.findByYonghuming(yonghuming);
    }


}
