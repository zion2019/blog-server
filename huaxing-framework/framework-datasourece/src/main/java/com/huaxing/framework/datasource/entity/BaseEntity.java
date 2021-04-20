package com.huaxing.framework.datasource.entity;

import com.baomidou.mybatisplus.annotation.*;
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
     * 创建人
     */
    @TableField(value = "created_by",fill = FieldFill.INSERT)
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新人
     */
    @TableField(value = "updated_by",fill =FieldFill.UPDATE)
    private String updatedBy;
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
