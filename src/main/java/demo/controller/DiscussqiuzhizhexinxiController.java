package demo.controller;


import demo.domain.Discussqiuzhizhexinxi;
import demo.mapper.QiuzhizhexinxiMapper;
import demo.service.IDiscussqiuzhizhexinxiService;
import demo.util.Result;
import demo.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.jni.BIOCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * <p>
 * 求职者信息评论表 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/discussqiuzhizhexinxi")
@Api(tags = "求职者信息评论")
public class DiscussqiuzhizhexinxiController {

    @Autowired
    private IDiscussqiuzhizhexinxiService discussqiuzhizhexinxiService;




    @PostMapping("/add")
    public Result add(@Valid @RequestBody Discussqiuzhizhexinxi discussqiuzhizhexinxi){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return discussqiuzhizhexinxiService.add(discussqiuzhizhexinxi,yonghuId);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除招聘信息评论")
    public Result delete(@PathVariable BigInteger id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();

        return discussqiuzhizhexinxiService.deleteSelf(id,yonghuId);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "修改自己求职者信息评论")
    public Result update(@Valid @RequestBody Discussqiuzhizhexinxi discussqiuzhizhexinxi,@PathVariable BigInteger id){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return discussqiuzhizhexinxiService.updateSelf(discussqiuzhizhexinxi,yonghuId,id);
    }


    @GetMapping("/selectSelf")
    @ApiOperation(value = "查看个人求职者信息评论")
    public Result selectSelf(){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();

        return discussqiuzhizhexinxiService.selectSelf(yonghuId);
    }


    @GetMapping("/select/{r_id}")
    @ApiOperation(value = "查看求职者评论")
    public Result select(@PathVariable BigInteger r_id){
        return discussqiuzhizhexinxiService.select(r_id);
    }


}

