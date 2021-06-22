package com.huaxing.resource.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

/**
 * 角色表
 * 
 * @author yechuyi
 * @email boss
 * @date 2021-04-19 16:47:11
 */
@Data
@TableName("sys_role")
public class SysRoleEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色编码
	 */
	@TableField(value = "code")
	private String code;
	/**
	 * 角色名称
	 */
	@TableField(value = "name")
	private String name;
	/**
	 * 备注
	 */
	@TableField(value = "remark")
	private String remark;


}
