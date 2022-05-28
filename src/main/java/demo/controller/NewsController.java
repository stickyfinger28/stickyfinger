package demo.controller;


import demo.domain.News;
import demo.service.INewsService;
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
 * 新闻资讯 前端控制器
 * </p>
 *
 * @author kim
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/news")
@Api(tags = "新闻资讯")
public class NewsController {

    @Autowired
    private INewsService newsService;


    @PostMapping("/add")
    @ApiOperation(value = "添加新闻") //需要admin_basic
    @PreAuthorize("hasAuthority('admin_basic')")
    public Result add(@Valid @RequestBody News news) {

        return newsService.add(news);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除新闻信息")//需要admin_basic
    @PreAuthorize("hasAuthority('admin_basic')")
    public Result delete(@PathVariable BigInteger id){
        return newsService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "修改新闻")//需要admin_basic
    @PreAuthorize("hasAuthority('admin_basic')")
    public Result update(@Valid @RequestBody News news ,@PathVariable BigInteger id) {
        return newsService.updateSelf(news,id);
    }

    @GetMapping("/select")
    @ApiOperation(value = "查看新闻")
    public Result selectSelf() {

        return newsService.select();
    }

}

