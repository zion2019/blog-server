package com.huaxing.resource.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-角色
 *
 * @email boss
 * @date 2021-04-19 16:47:11
 */
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色id
	 */
	@TableField(value = "role_id")
	private Long roleId;
	/**
	 * 用户id
	 */
	@TableField(value = "user_id")
	private Long userId;


}
