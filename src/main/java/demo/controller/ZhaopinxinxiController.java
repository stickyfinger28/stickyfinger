package demo.controller;


import demo.domain.L.LZhaopinxinxi;
import demo.domain.Yonghu;
import demo.domain.Zhaopinxinxi;
import demo.service.IZhaopinxinxiService;
import demo.util.Result;
import demo.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * <p>
 * 招聘信息 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/zhaopinxinxi")
@Api(tags = "招聘信息")
public class ZhaopinxinxiController {

    @Autowired
    private IZhaopinxinxiService zhaopinxinxiService;
    @PostMapping("/add")
    @ApiOperation(value = "添加招聘信息") //需要boss_basic
    @PreAuthorize("hasAuthority('boss_basic')")
    public Result add(@Valid @RequestBody Zhaopinxinxi zhaopinxinxi) {
        Yonghu boss = WebUtils.getLoginUser().getYonghu();
        return zhaopinxinxiService.add(zhaopinxinxi,boss);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除招聘信息")//需要boss_basic
    @PreAuthorize("hasAuthority('boss_basic')")
    public Result delete(@PathVariable BigInteger id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return zhaopinxinxiService.delete(id,yonghuId);
    }

    @PutMapping("/update")//需要boss_basic
    @ApiOperation(value = "修改招聘信息")
    @PreAuthorize("hasAuthority('boss_basic')")
    public Result update(@Valid @RequestBody LZhaopinxinxi lzhaopinxinx) {
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return  zhaopinxinxiService.updateSelf(lzhaopinxinx,yonghu);
    }

    @GetMapping("/selectSelf")//需要boss_basic
    @PreAuthorize("hasAuthority('boss_basic')")
    @ApiOperation(value = "查看自己发布的招聘信息")
    public Result selectSelf() {
        Yonghu boss = WebUtils.getLoginUser().getYonghu();
        return zhaopinxinxiService.selectSelf(boss);
    }

    @GetMapping("/select")//无需权限
    @ApiOperation(value = "查看招聘信息")
    public Result select() {
        return zhaopinxinxiService.select();
    }

    @PutMapping("/dianzan")
    @ApiOperation(value = "点赞")
    @ApiImplicitParam(name = "id",required = true)
    public Result dianzan(Integer id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return zhaopinxinxiService.dianzhan(id,yonghuId);
    }

    @PutMapping("/diancai")
    @ApiOperation(value = "点踩")
    @ApiImplicitParam(name = "id",required = true)
    public Result diancai(Integer id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return zhaopinxinxiService.diancai(id,yonghuId);
    }



}

