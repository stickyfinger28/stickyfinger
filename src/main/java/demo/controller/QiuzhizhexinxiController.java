package demo.controller;


import demo.domain.L.LQiuzhizhexinxi;
import demo.domain.Qiuzhizhexinxi;
import demo.domain.Yonghu;
import demo.service.IQiuzhizhexinxiService;
import demo.util.Code;
import demo.util.Result;
import demo.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * <p>
 * 求职者信息 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/qiuzhizhexinxi")
@Api(tags = "求职者信息")
public class QiuzhizhexinxiController {

    @Autowired
    private IQiuzhizhexinxiService qiuzhizhexinxiService;


    @ApiOperation(value = "上传求职信息")
    @PostMapping("/add")
    public Result add(@Valid @RequestBody Qiuzhizhexinxi qiuzhizhexinxi) {
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return qiuzhizhexinxiService.add(qiuzhizhexinxi,yonghu);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除求职者信息") //需要staff_basic
    @PreAuthorize("hasAuthority('staff_basic')")
    public Result delete(@PathVariable BigInteger id){
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return qiuzhizhexinxiService.delete(id,yonghu);
    }

    @ApiOperation(value = "修改求职信息")
    @PutMapping("/update") //需要staff_basic
    @PreAuthorize("hasAuthority('staff_basic')")
    public Result update(@RequestBody LQiuzhizhexinxi lqiuzhizhexinxi){
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return qiuzhizhexinxiService.updateSelf(lqiuzhizhexinxi,yonghu);
    }

    @ApiOperation(value = "查找个人求职信息")
    @GetMapping("/selectSelf") //需要staff_basic
    @PreAuthorize("hasAnyAuthority('staff_basic')")
    public Result selectSelf(){
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return qiuzhizhexinxiService.selectSelf(yonghu);
    }

    @ApiOperation(value = "查看求职信息")
    @GetMapping("/selectAll") //需要boss_basic
    @PreAuthorize("hasAuthority('boss_basic')")
    public Result selectAll(){
        return qiuzhizhexinxiService.selectAll();
    }

    @PutMapping("/dianzan/{id}")
    @ApiOperation(value = "点赞")
    @ApiImplicitParam(name = "id",required = true)
    public Result dianzan(@PathVariable Integer id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return qiuzhizhexinxiService.dianzhan(id,yonghuId);
    }

    @PutMapping("/diancai{id}")
    @ApiOperation(value = "点踩")
    @ApiImplicitParam(name = "id",required = true)
    public Result diancai(@PathVariable Integer id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return qiuzhizhexinxiService.diancai(id,yonghuId);
    }


}

