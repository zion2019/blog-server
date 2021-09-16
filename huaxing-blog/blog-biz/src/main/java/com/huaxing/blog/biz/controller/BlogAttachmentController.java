package com.huaxing.blog.biz.controller;

import com.huaxing.blog.biz.service.BlogAttachmentService;
import com.huaxing.framework.core.response.BaseAttachmentVo;
import com.huaxing.framework.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zion
 * @Description 博文附件管理
 **/
@Api(tags = "博文管理")
@RestController
@RequestMapping("/file")
public class BlogAttachmentController {

    @Autowired
    private BlogAttachmentService service;

    /**
     * 为了节省服务器资源，做一个简易的文件上传，通过nginx访问图片
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("上传博客图片")
    public ResponseResult<BaseAttachmentVo> upload(@RequestPart("file")MultipartFile file){
        return ResponseResult.ok(service.upload(file));
    }

}
