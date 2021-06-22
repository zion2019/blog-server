package com.huaxing.common.biz.service;

import com.huaxing.common.api.vo.BaseFileVo;
import com.huaxing.framework.core.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zion
 * @Description 文件上传服务
 **/
public interface FileService {

    /**
     * 上传
     * @param file
     * @return
     */
    ResponseResult<BaseFileVo> upload(MultipartFile file);
}
