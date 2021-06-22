package com.huaxing.common.biz.service.impl;

import com.huaxing.common.api.vo.BaseFileVo;
import com.huaxing.common.biz.service.FileService;
import com.huaxing.framework.core.response.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zion
 * @Description 文件服务-阿里云实现
 * TODO 目前只有阿里云服务器，后期不直接加入容器，根据yml配置加载文件服务器类型
 **/
@Service
public class AliyunFileServiceImpl implements FileService {


    @Override
    public ResponseResult<BaseFileVo> upload(MultipartFile file) {


        return null;
    }
}
