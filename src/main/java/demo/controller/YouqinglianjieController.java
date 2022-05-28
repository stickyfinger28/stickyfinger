package demo.controller;


import demo.domain.Youqinglianjie;
import demo.service.IYingpinxinxiService;
import demo.service.IYouqinglianjieService;
import demo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * <p>
 * 友情链接 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/youqinglianjie")
@Api(tags = "友情链接")
public class YouqinglianjieController {

    @Autowired
    private IYouqinglianjieService youqinglianjieService;

    @PostMapping("/add")
    @ApiOperation(value = "添加友情链接")
    @PreAuthorize("hasAuthority('admin_basic')")
    public Result add(@Valid @RequestBody Youqinglianjie youqinglianjie) {

       return youqinglianjieService.add(youqinglianjie);

    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除友情链接")
    @PreAuthorize("hasAuthority('admin_basic')")
    public Result delete(@PathVariable BigInteger id) {
        return youqinglianjieService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "修改友情链接")
    @PreAuthorize("hasAuthority('admin_basic')")
    public Result update(@Valid @RequestBody Youqinglianjie youqinglianjie, @PathVariable BigInteger id) {
        return youqinglianjieService.updateSelf(youqinglianjie,id);
    }

    @GetMapping("/select")
    @ApiOperation(value = "查看友情链接")
    public Result selectSelf() {

        return youqinglianjieService.select();
    }
}

