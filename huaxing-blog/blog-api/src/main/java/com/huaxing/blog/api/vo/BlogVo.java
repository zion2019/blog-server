package com.huaxing.blog.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huaxing.framework.core.page.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Zion
 * @Description 博文信息
 **/
@Data
@ApiModel("博文信息")
public class BlogVo  extends BasePageRequest {

    @ApiModelProperty("主键Id")
    private Long id;

    @ApiModelProperty("标题")
    @NotNull(message = "缺失标题")
    private String title;

    @ApiModelProperty("类别ID")
    @NotNull(message = "缺失类别ID")
    private Long categoryId;

    @ApiModelProperty("类别名称")
    private String categoryName;

    @ApiModelProperty("封面图片")
    @NotNull(message = "缺失封面图片")
    private String coverImg;

    @ApiModelProperty("博文样式标识")
    private String styles;

    @ApiModelProperty("博文内容")
    private  String content;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    @ApiModelProperty("简介说明")
    private String profile;

    @ApiModelProperty("评论数")
    private Integer messageCount;

    @ApiModelProperty("热度，查看数")
    private Integer viewCount;

    @ApiModelProperty(value = "是否为管理员")
    private Integer admin;
}
