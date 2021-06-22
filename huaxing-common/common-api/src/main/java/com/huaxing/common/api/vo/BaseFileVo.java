package com.huaxing.common.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zion
 * @Description 文件信息
 **/
@Data
@ApiModel("文件信息")
public class BaseFileVo {

    @ApiModelProperty("文件名")
    private String fileName;

    @ApiModelProperty("源文件名")
    private String originalFileName;

    @ApiModelProperty("文件大小")
    private Long fileSize;

    @ApiModelProperty("文件类型")
    private String fileType;

    @ApiModelProperty("文件Key")
    private String fileKey;

    @ApiModelProperty("文件访问地址")
    private String url;

    @ApiModelProperty("文件拓展类型")
    private String extendName;
}
