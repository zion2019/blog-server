package com.huaxing.blog.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zion
 * @Description 博文类别VO
 **/
@Data
@ApiModel("博文类别")
public class BlogCategoryVo {

    /**
     * 类别名称
     */
    @ApiModelProperty("类别名称")
    private String name;
    /**
     * 类别编码
     */
    @ApiModelProperty("类别编码")
    private String code;
    /**
     * 封面图片
     */
    @ApiModelProperty("封面图片")
    private String coverImg;
}
