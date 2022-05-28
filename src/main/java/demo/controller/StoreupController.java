package demo.controller;


import demo.domain.Storeup;
import demo.service.IStoreupService;
import demo.util.Result;
import demo.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/storeup")
@Api(tags = "收藏")
public class StoreupController {

    @Autowired
    private IStoreupService storeupService;

    @PostMapping("/add")
    @ApiOperation(value = "添加自己的收藏")
    public Result add(@Valid @RequestBody Storeup storeup) {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return storeupService.add(storeup,yonghuId);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除收藏")
    public Result delete(@PathVariable BigInteger id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return storeupService.delete(id,yonghuId);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改自己的收藏")
    public Result update(@Valid @RequestBody Storeup storeup) {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return storeupService.updateSelf(storeup,yonghuId);
    }

    @GetMapping("/selectSelf")
    @ApiOperation(value = "查看自己的收藏")
    public Result selectSelf() {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return storeupService.selectSelf(yonghuId);
    }
}

