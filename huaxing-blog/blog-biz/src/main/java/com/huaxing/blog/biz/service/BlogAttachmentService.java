package com.huaxing.blog.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huaxing.blog.biz.entity.BlogAttachmentEntity;
import com.huaxing.blog.biz.entity.BlogContentEntity;
import com.huaxing.framework.core.response.BaseAttachmentVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 博文内容
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
public interface BlogAttachmentService extends IService<BlogAttachmentEntity> {

    /**
     * 上传图片文件
     * @param file
     * @return
     */
    BaseAttachmentVo upload(MultipartFile file);

}

