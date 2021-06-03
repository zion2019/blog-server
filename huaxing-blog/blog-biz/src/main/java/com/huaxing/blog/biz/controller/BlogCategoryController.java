package com.huaxing.blog.biz.controller;

import com.huaxing.blog.api.vo.BlogCategoryVo;
import com.huaxing.blog.biz.service.BlogCategoryService;
import com.huaxing.framework.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation("新增OR编辑博文")
    public ResponseResult<Boolean> save(@RequestBody BlogCategoryVo vo){
        return null;
    }

}
