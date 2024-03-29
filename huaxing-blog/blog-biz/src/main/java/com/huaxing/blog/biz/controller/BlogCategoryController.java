package com.huaxing.blog.biz.controller;

import com.huaxing.blog.api.vo.BlogCategoryVo;
import com.huaxing.blog.biz.annotation.TempAuthControl;
import com.huaxing.blog.biz.service.BlogCategoryService;
import com.huaxing.framework.api.vo.SelectionVo;
import com.huaxing.framework.core.page.PageDto;
import com.huaxing.framework.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Zion
 * @Description 博文类别控制器
 **/
@Api(tags = "博文类别")
@RestController
@RequestMapping("/category")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService service;

    @PostMapping
    @ApiOperation("新增OR编辑博文类别")
    @TempAuthControl
    public ResponseResult<Boolean> save(@RequestBody @Valid BlogCategoryVo vo){
        return service.saveOrEdit(vo);
    }

    @GetMapping
    @ApiOperation("分页查询类别")
    @TempAuthControl
    public ResponseResult<PageDto<BlogCategoryVo>> page(BlogCategoryVo vo){
        return ResponseResult.ok(service.page(vo));
    }

    @GetMapping("/selection")
    @ApiOperation("博客分类下拉框")
    public ResponseResult<List<SelectionVo>> selection(){
        return ResponseResult.ok(service.selection());
    }

    @DeleteMapping
    @ApiOperation("删除分类")
    @TempAuthControl
    public ResponseResult<Boolean> removeByIds(Long id){
        return ResponseResult.ok(service.removeById(id));
    }

}
