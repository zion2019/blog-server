package com.huaxing.site.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统菜单
 *
 * @email boss
 * @date 2021-04-19 16:47:10
 */
@Data
@TableName("sys_menu")
public class SysMenuEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 类型 0 目录 1 菜单 2 按钮权限
	 */
	@TableField(value = "type")
	private String type;
	/**
	 * 菜单code(非必填,流程中标识动作编码)
	 */
	@TableField(value = "code")
	private String code;
	/**
	 * 菜单名称
	 */
	@TableField(value = "name")
	private String name;
	/**
	 * 上级菜单ID
	 */
	@TableField(value = "pid")
	private Long pid;
	/**
	 * 排序
	 */
	@TableField(value = "sort")
	private Integer sort;
	/**
	 * 图标
	 */
	@TableField(value = "icon")
	private String icon;
	/**
	 * 接口地址
	 */
	@TableField(value = "path")
	private String path;
	/**
	 * 菜单权限标识
	 */
	@TableField(value = "permission")
	private String permission;
	/**
	 * 路由缓冲
	 */
	@TableField(value = "keep_alive")
	private String keepAlive;


}
