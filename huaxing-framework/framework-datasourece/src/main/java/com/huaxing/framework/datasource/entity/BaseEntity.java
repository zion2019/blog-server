package com.huaxing.framework.datasource.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Zion
 * @Description 基类
 **/
@Data
public class BaseEntity implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 创建人Id
     */
    @TableField(value = "created_id",fill = FieldFill.INSERT)
    private Long createdId;

    /**
     * 创建人
     */
    @TableField(value = "created_name",fill = FieldFill.INSERT)
    private String createdName;

    /**
     * 创建时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * 更新人
     */
    @TableField(value = "updated_id",fill =FieldFill.UPDATE)
    private Long updatedId;

    /**
     * 更新人
     */
    @TableField(value = "updated_name",fill =FieldFill.UPDATE)
    private String updatedName;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time",fill =FieldFill.UPDATE)
    private Date updatedTime;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;

}
