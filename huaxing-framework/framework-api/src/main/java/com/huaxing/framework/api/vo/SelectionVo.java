package com.huaxing.framework.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zion
 * @Description 下拉框信息
 **/
@Data
@ApiModel("下拉框信息")
public class SelectionVo {

    @ApiModelProperty("值（ID）")
    private Long value;

    @ApiModelProperty("名称")
    private String label;
}
