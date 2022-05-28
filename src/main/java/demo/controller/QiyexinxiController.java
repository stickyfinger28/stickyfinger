package demo.controller;


import demo.domain.L.LQiyexinxi;
import demo.domain.Qiyexinxi;
import demo.domain.Yonghu;
import demo.service.IQiyexinxiService;
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
 * 企业信息 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/qiyexinxi")
@Api(tags = "企业信息")
public class QiyexinxiController {
    @Autowired
    private IQiyexinxiService qiyexinxiService;

    @PostMapping("/add")
    @ApiOperation(value = "添加企业信息") //TODO 赋予Boss角色
    public Result add(@Valid @RequestBody Qiyexinxi qiyexinxi) {
        Yonghu boss = WebUtils.getLoginUser().getYonghu();
        return  qiyexinxiService.add(qiyexinxi,boss);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除企业信息") //需要boss_basic
    @PreAuthorize("hasAuthority('boss_basic')")
    public Result delete(@PathVariable BigInteger id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return qiyexinxiService.delete(id,yonghuId);
    }

    @PutMapping("/update") //需要boss_basic
    @ApiOperation(value = "修改企业信息")
    @PreAuthorize("hasAuthority('boss_basic')")
    public Result update(@Valid @RequestBody LQiyexinxi lQiyexinxi) {
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return qiyexinxiService.updateSelf(yonghu,lQiyexinxi);
    }

    @GetMapping("/select") //无需权限
    @ApiOperation(value = "查找企业信息")
    public Result select() {
        return qiyexinxiService.select();
    }

    @GetMapping("/selectSelf") //需要boss_basic
    @ApiOperation(value = "查找个人企业信息")
    @PreAuthorize("hasAuthority('boss_basic')")
    public Result selectSelf() {
        Yonghu boss = WebUtils.getLoginUser().getYonghu();
        return qiyexinxiService.selectSelf(boss);
    }
}

