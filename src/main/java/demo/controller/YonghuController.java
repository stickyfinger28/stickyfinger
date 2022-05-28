package demo.controller;


import demo.domain.L.Lyonghu;
import demo.domain.Yonghu;
import demo.service.IYonghuService;
import demo.util.Result;
import demo.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/yonghu")
@Api(tags = "用户")
public class YonghuController {


    @Autowired
    private IYonghuService yonghuService;

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public Result register(@Valid @RequestBody Yonghu yonghu) {
        return yonghuService.register(yonghu);
    }


    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result login(@Valid @RequestBody Lyonghu lyonghu) {
        String yonghuming = lyonghu.getYonghuming();
        String mima = lyonghu.getMima();
        return yonghuService.login(yonghuming, mima);
    }

    @DeleteMapping("/logout")
    @ApiOperation(value = "登出")
    public Result logout() {
        return yonghuService.logout();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "注销")
    public Result delete(@PathVariable BigInteger id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return yonghuService.delete(id,yonghuId);
    }


    @ApiOperation(value = "修改用户基本信息")
    @PutMapping("/update")
    public Result update(@RequestBody Yonghu Yonghu) {
        Yonghu loginYonghu = WebUtils.getLoginUser().getYonghu();
        return yonghuService.updateSelf(Yonghu, loginYonghu);
    }

    @GetMapping("/selectSelf")
    @ApiOperation(value = "查询个人信息")
    public Result selectSelf() {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return yonghuService.selectSelf(yonghuId);
    }


    @ApiOperation(value = "查询所有用户信息")
    @PreAuthorize("hasAuthority('admin_basic')")
    @GetMapping("selectAllYonghu")
    public Result selectAllYonghu() {
        return yonghuService.selectAllYonghu();
    }


    @ApiOperation(value = "赋予用户管理员权限")
    @PutMapping("setAdmin/{id}")
    @PreAuthorize("hasAuthority('admin_basic')")
    public Result setAdmin(@PathVariable BigInteger id){
        return yonghuService.setAdmin(id);
    }


    @ApiOperation(value = "用户名模糊查询")
    @GetMapping("select/{yonghuming}")
    public Result selectWithYonghuming(@PathVariable String yonghuming){
        return yonghuService.selectWithYonghuming(yonghuming);
    }
}

