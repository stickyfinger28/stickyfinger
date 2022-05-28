package demo.controller;


import demo.domain.Messages;
import demo.service.IMessagesService;
import demo.util.Result;
import demo.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

/**
 * <p>
 * 留言板 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/messages")
@Api(tags = "留言版")
public class MessagesController {
    @Autowired
    private IMessagesService messagesService;


    @PostMapping("/add")
    @ApiOperation(value = "添加留言信息")
    public Result add(@Valid @RequestBody Messages messages) {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();

        return messagesService.add(messages,yonghuId);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除留言信息")
    @ApiImplicitParam(name = "id",required = true,paramType = "form")
    public Result delete( BigInteger id) {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return messagesService.delete(id,yonghuId);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改留言信息")
    public Result update(@Valid @RequestBody Messages messages) {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return messagesService.updateSelf(messages,yonghuId);
    }

    @GetMapping("/selectSelf")
    @ApiOperation(value = "查看自己的留言信息")
    public Result selectSelf() {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();

        return messagesService.selectSelf(yonghuId);
    }
}

