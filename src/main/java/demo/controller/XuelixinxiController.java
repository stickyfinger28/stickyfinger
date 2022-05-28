package demo.controller;


import demo.domain.Xuelixinxi;
import demo.domain.Yonghu;
import demo.service.IXuelixinxiService;
import demo.util.Code;
import demo.util.Result;
import demo.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Objects;

/**
 * <p>
 * 学历信息 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/xuelixinxi")
@Api(tags = "学历信息")
public class XuelixinxiController {
    @Autowired
    private IXuelixinxiService xuelixinxiService;
    @PostMapping("/add")
    @ApiOperation(value = "添加学历信息")
    public Result add(@Valid @RequestBody Xuelixinxi xuelixinxi) {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return xuelixinxiService.add(xuelixinxi,yonghuId);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除学历信息")
    public Result delete() {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        return xuelixinxiService.delete(yonghuId);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改学历信息")
    public Result update(@Valid @RequestBody Xuelixinxi xuelixinxi) {
        Yonghu yonghu = WebUtils.getLoginUser().getYonghu();
        return xuelixinxiService.updateSelf(xuelixinxi,yonghu);
    }

    @GetMapping("/selectSelf")
    @ApiOperation(value = "查看自己学历信息")
    public Result selectSelf() {
        BigInteger yonghuId = WebUtils.getLoginUser().getYonghu().getId();
        Xuelixinxi xuelixinxi = xuelixinxiService.getById(yonghuId);
        if (Objects.isNull(xuelixinxi)){
            return new Result(Code.GET_ERR,"你还未添加学历信息哦");
        }
        return new Result(Code.GET_OK,xuelixinxi,"查看个人学历信息成功");
    }

}

