package demo.controller;


import demo.domain.Discusszhaopinxinxi;
import demo.domain.Yonghu;
import demo.service.IDiscusszhaopinxinxiService;
import demo.util.Result;
import demo.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * <p>
 * 招聘信息评论表 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/discusszhaopinxinxi")
@Api(tags = "招聘信息评论")
public class DiscusszhaopinxinxiController {

    @Autowired
    private IDiscusszhaopinxinxiService discusszhaopinxinxiService;

    @PostMapping("/add")
    @ApiOperation(value = "添加招聘信息评论")
    public Result add(@Valid @RequestBody Discusszhaopinxinxi discusszhaopinxinxi) {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();

        return discusszhaopinxinxiService.add(discusszhaopinxinxi,yonghuId);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除招聘信息评论")
    public Result delete(@PathVariable BigInteger id) {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return discusszhaopinxinxiService.delete(id,yonghuId);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "更改招聘信息评论")
    public Result update(@Valid @RequestBody Discusszhaopinxinxi discusszhaopinxinxi,@PathVariable BigInteger id) {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return discusszhaopinxinxiService.updateSelf(discusszhaopinxinxi,yonghuId,id);
    }

    @GetMapping("/selectSelf")
    @ApiOperation(value = "查看自己的招聘信息评论")
    public Result selectSelf(){
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return discusszhaopinxinxiService.selectSelf(yonghuId);
    }

    @GetMapping("/select/{r_id}")
    @ApiOperation(value = "查看招聘信息评论")
    public Result select(@PathVariable BigInteger r_id) {
        return discusszhaopinxinxiService.select(r_id);
    }


}

