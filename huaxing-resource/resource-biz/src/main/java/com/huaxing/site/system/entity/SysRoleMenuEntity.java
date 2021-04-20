package com.huaxing.site.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author yechuyi
 * @email boss
 * @date 2021-04-19 16:47:11
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenuEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@TableField(value = "role_id")
	private Long roleId;
	/**
	 * 菜单ID
	 */
	@TableField(value = "menu_id")
	private Long menuId;

}
