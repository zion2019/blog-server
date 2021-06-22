package com.huaxing.common.biz.controller;

import com.huaxing.common.api.vo.BaseFileVo;
import com.huaxing.common.biz.service.FileService;
import com.huaxing.framework.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zion
 **/
@Api(tags = "文件服务")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("上传MultipartFile文件")
    @PostMapping(value="/upload/multipart",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<BaseFileVo> upload(@RequestPart("file") MultipartFile file){
        return fileService.upload(file);
    }
}
