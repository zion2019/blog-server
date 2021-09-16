package com.huaxing.blog.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 博文附件存储记录
 */
@Data
@TableName("blog_attachment")
public class BlogAttachmentEntity extends BaseEntity implements Serializable {

    /**
     * 文件名
     */
    private String fileName;
    /**
     * 原文件名
     */
    private String originalFileName;
    /**
     * 文件业务类型
     */
    private Integer businessType;

    /**
     * 文件业务ID
     */
    private Long businessId;

    /**
     * 拓展名
     */
    private String extendName;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 附件说明
     */
    private String reason;
}
