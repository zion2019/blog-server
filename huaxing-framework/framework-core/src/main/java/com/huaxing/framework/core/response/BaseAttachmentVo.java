package com.huaxing.framework.core.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BaseAttachmentVo {

    private Long id;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;


}
