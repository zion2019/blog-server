package com.huaxing.site.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @email boss
 * @date 2021-04-19 16:47:11
 */
@Data
@TableName("sys_user")
public class SysUserEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名称
	 */
	@TableField(value = "name")
	private String name;
	/**
	 * 手机号码
	 */
	@TableField(value = "telephone")
	private String telephone;
	/**
	 * 账户名称
	 */
	@TableField(value = "account")
	private String account;
	/**
	 * 用户密码
	 */
	@TableField(value = "password")
	private String password;
	/**
	 * 用户盐值
	 */
	@TableField(value = "salt")
	private String salt;
	/**
	 * 头像
	 */
	@TableField(value = "avatar")
	private String avatar;
	/**
	 * 是否停用 0:停用 1:有效
	 */
	@TableField(value = "enabled")
	private Integer enabled;


}
