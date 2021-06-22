package com.huaxing.resource.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

/**
 * 字典明细信息
 * 
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-06-08 15:28:47
 */
@Data
@TableName("sys_dict_item")
public class SysDictItemEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关联主表ID
	 */
	@TableField(value = "dict_id")
	private Long dictId;
	/**
	 * 字典类型code
	 */
	@TableField(value = "dict_type")
	private String dictType;
	/**
	 * 标签名
	 */
	@TableField(value = "label")
	private String label;
	/**
	 * 字典值
	 */
	@TableField(value = "value")
	private String value;
	/**
	 * 描述
	 */
	@TableField(value = "description")
	private String description;
	/**
	 * 排序
	 */
	@TableField(value = "sort")
	private Integer sort;


}
