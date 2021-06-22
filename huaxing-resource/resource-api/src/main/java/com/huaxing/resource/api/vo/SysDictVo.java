package com.huaxing.resource.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Zion
 * @Description
 **/
@Data
@ApiModel("字典信息")
public class SysDictVo {

    @ApiModelProperty("字典ID")
    private Long id;

    @ApiModelProperty("字典类型 CODE")
    @NotNull(message = "字典类型为空")
    private String dictType;

    @ApiModelProperty("字典名称")
    @NotNull(message = "字典名称为空")
    private String name;

    @ApiModelProperty("描述")
    private String description;
}
