package com.huaxing.blog.biz.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.blog.biz.entity.BlogAttachmentEntity;
import com.huaxing.blog.biz.entity.BlogContentEntity;
import com.huaxing.blog.biz.mapper.BlogAttachmentMapper;
import com.huaxing.blog.biz.mapper.BlogContentMapper;
import com.huaxing.blog.biz.service.BlogAttachmentService;
import com.huaxing.blog.biz.service.BlogContentService;
import com.huaxing.framework.core.response.BaseAttachmentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;

/**
 * 博文附件存储
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Slf4j
@Service("blogAttachmentServiceImpl")
public class BlogAttachmentServiceImpl extends ServiceImpl<BlogAttachmentMapper, BlogAttachmentEntity> implements BlogAttachmentService {

    @Override
    public BaseAttachmentVo upload(MultipartFile file) {
        BaseAttachmentVo result = new BaseAttachmentVo();
        FileInputStream ins = null;
        try{
            LocalDateTime now = LocalDateTime.now();
            int year = now.getYear();
            int month = now.getMonthValue();
            // 当前用户工作目录，容器中是在jar包同级目录下
            String baseDir = System.getProperty("user.dir")+File.separator+"image";

            String datePathFormat = "/%s/%s";
            String datePathDir = String.format(datePathFormat,year,month);
            String fullDirPath = baseDir + datePathDir;

            // 项目是容器部署，直接写到根目录下的，当月的文件夹下
            if(!FileUtil.exist(fullDirPath)){
                FileUtil.mkdir(fullDirPath);
            }

            // 写入文件
            String fullPath = fullDirPath + File.separator + file.getOriginalFilename();
            log.info("write file:{}",fullPath);
            FileUtil.writeFromStream(file.getInputStream(),new File(fullPath));

            result.setFileUrl(datePathDir+ File.separator + file.getOriginalFilename());
            result.setFileName(file.getOriginalFilename());
        }catch (Exception e){
            log.error("upload file error",e);
        }finally {

        }
        return result;
    }
}