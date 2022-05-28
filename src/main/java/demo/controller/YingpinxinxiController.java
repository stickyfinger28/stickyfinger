package demo.controller;


import demo.domain.L.LYingpinxinxi;
import demo.domain.L.SYingpinxinxi;
import demo.domain.Yingpinxinxi;
import demo.domain.Yonghu;
import demo.service.IYingpinxinxiService;
import demo.util.Result;
import demo.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * <p>
 * 应聘信息 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/yingpinxinxi")
@Api(tags = "应聘信息")
public class YingpinxinxiController {

    @Autowired
    private IYingpinxinxiService yingpinxinxiService;

    @PostMapping("/add")
    @ApiOperation(value = "添加应聘信息")
    @PreAuthorize("hasAuthority('staff_basic')")
    public Result add(@Valid  @RequestBody  Yingpinxinxi yingpinxinxi) {
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return yingpinxinxiService.add(yingpinxinxi,yonghu);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('staff_basic')")
    public Result delete(@PathVariable BigInteger id){
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return yingpinxinxiService.delete(id,yonghu);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改应聘信息")
    @PreAuthorize("hasAuthority('staff_basic')")
    public Result updateSelf(@Valid @RequestBody LYingpinxinxi lYingpinxinxi) {
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return yingpinxinxiService.updateSelf(lYingpinxinxi,yonghu);
    }

    @GetMapping("/selectSelf")
    @ApiOperation(value = "查看自己的应聘信息")
    @PreAuthorize("hasAuthority('staff_basic')")
    public Result selectSelf() {
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return yingpinxinxiService.selectSelf(yonghu);
    }

    @GetMapping("/select")//需要boss_basic
    @ApiOperation(value = "查看应聘自己岗位的应聘信息")
    @PreAuthorize("hasAuthority('boss_basic')")
    public Result select() {
        Yonghu boss = WebUtils.getLoginUser().getYonghu();
        return yingpinxinxiService.select(boss);
    }

    @PutMapping("/updateSh")
    @ApiOperation(value = "审核应聘信息")
    @PreAuthorize("hasAuthority('boss_basic')")//需要boss_basic
    public Result updateSh(@Valid @RequestBody SYingpinxinxi syingpinxinxi){
        Yonghu boss = WebUtils.getLoginUser().getYonghu();
        return yingpinxinxiService.updateSh(syingpinxinxi,boss);
    }

}

