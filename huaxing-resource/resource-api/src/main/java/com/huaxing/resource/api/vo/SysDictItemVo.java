package com.huaxing.resource.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Zion
 * @Description 字典明细
 **/
@Data
@ApiModel("字典明细信息")
public class SysDictItemVo implements Serializable {

    @ApiModelProperty("字典明细ID")
    private Long id;

    /**
     * 关联主表ID
     */
    @NotNull(message = "字典主项ID缺失")
    @ApiModelProperty("关联主表ID")
    private Long dictId;
    /**
     * 字典类型code
     */
    @NotNull(message = "字典类型CODE缺失")
    @ApiModelProperty("字典类型code")
    private String dictType;
    /**
     * 标签名
     */
    @NotNull(message = "标签名缺失")
    @ApiModelProperty("标签名")
    private String label;
    /**
     * 字典值
     */
    @NotNull(message = "字典值缺失")
    @ApiModelProperty("字典值")
    private String value;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

}
