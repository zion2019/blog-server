package com.huaxing.resource.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典主表
 * 
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-06-08 15:28:47
 */
@Data
@TableName("sys_dict")
public class SysDictEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 字典类型code
	 */
	@TableField(value = "dict_type")
	private String dictType;

	/**
	 * 字典名称
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 字典描述
	 */
	@TableField(value = "description")
	private String description;

}
