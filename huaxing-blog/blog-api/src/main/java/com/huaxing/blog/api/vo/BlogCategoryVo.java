package com.huaxing.blog.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huaxing.framework.core.page.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author Zion
 * @Description 博文类别VO
 **/
@Data
@ApiModel("博文类别")
public class BlogCategoryVo extends BasePageRequest {

    /**
     * Id
     */
    private Long id;

    /**
     * 类别名称
     */
    @NotEmpty(message = "类别名称不能为空")
    @ApiModelProperty("类别名称")
    private String name;

    /**
     * 类别编码
     */
    @NotEmpty(message = "类别编码不能为空")
    @ApiModelProperty("类别编码")
    private String code;

    /**
     * 封面图片
     */
    @ApiModelProperty("封面图片")
    private String coverImg;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
}
