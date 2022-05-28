package demo.controller;


import demo.domain.Discussqiuzhizhexinxi;
import demo.domain.Gangweifenlei;
import demo.service.IGangweifenleiService;
import demo.util.Code;
import demo.util.Result;
import demo.util.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 岗位分类 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/gangweifenlei")
@Api(tags = "岗位分类")
public class GangweifenleiController {

    @Autowired
    private IGangweifenleiService gangweifenleiService;


    @ApiOperation("新增岗位")
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('admin_basic','boss_basic')")
    public Result add(@Valid @RequestBody List<Gangweifenlei> gangweifenleiList){
        return gangweifenleiService.saveByList(gangweifenleiList);
    }

    @ApiOperation("删除岗位")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('admin_basic','boss_basic')")
    public Result delete(@PathVariable BigInteger id){
        boolean b = gangweifenleiService.removeById(id);
        if (!b){
            return new Result(Code.DELETE_ERR,"删除失败");
        }
        return new Result(Code.DELETE_OK,"删除成功");
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "修改岗位分类信息")
    @PreAuthorize("hasAnyAuthority('admin_basic','boss_basic')")
    public Result update(@Valid @RequestBody Gangweifenlei gangweifenlei){

        return gangweifenleiService.updateSelf(gangweifenlei);
    }

    @GetMapping("/select")
    @ApiOperation(value = "查询岗位分类")
    public Result select(){
        return gangweifenleiService.select();
    }


}

