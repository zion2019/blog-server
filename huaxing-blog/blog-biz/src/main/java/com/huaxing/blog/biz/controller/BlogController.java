package com.huaxing.blog.biz.controller;

import com.huaxing.blog.api.vo.BlogVo;
import com.huaxing.blog.biz.annotation.TempAuthControl;
import com.huaxing.blog.biz.service.BlogBasicService;
import com.huaxing.framework.core.page.PageDto;
import com.huaxing.framework.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zion
 * @Description 博文管理
 **/
@Api(tags = "博文管理")
@RestController
@RequestMapping("/post")
public class BlogController {

    @Autowired
    private BlogBasicService service;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public ResponseResult<PageDto<BlogVo>> pages(BlogVo vo){
        return ResponseResult.ok(service.pages(vo));
    }

    @PostMapping
    @ApiOperation("新增编辑博文")
    @TempAuthControl
    public ResponseResult<Boolean> saveEditBlog(@RequestBody @Validated BlogVo vo){
        return ResponseResult.ok(service.saveEditBlog(vo));
    }

    @DeleteMapping
    @ApiOperation("删除博文")
    @TempAuthControl
    public ResponseResult<Boolean> removeById(Long id){
        return ResponseResult.ok(service.removeBlog(id));
    }

    @GetMapping("/info/{basicId}")
    @ApiOperation("获取博文内容")
    public ResponseResult<BlogVo> getContent(@PathVariable("basicId") Long basicId){
        return ResponseResult.ok(service.info(basicId));
    }

    @PutMapping("/release")
    @ApiOperation("上架博文")
    public ResponseResult<Boolean> release(@RequestParam("basicId") Long basicId,@RequestParam("releaseStatus")Integer releaseStatus){
        return ResponseResult.ok(service.release(basicId,releaseStatus));
    }

}
