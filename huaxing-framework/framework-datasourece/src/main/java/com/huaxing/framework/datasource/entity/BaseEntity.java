package com.huaxing.framework.datasource.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Zion
 * @Description 基类
 **/
@Data
public class BaseEntity implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
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
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;
}
