package com.huaxing.site.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

/**
 * 
 * 
 * @author yechuyi
 * @email boss
 * @date 2020-09-23 18:26:01
 */
@Data
@TableName("sys_user")
public class SysUserEntity extends BaseEntity {

	/**
	 * 唯一标识
	 */
	@TableField(value = "user_id")
	private String userId;
	/**
	 * 用户名
	 */
	@TableField(value = "user_name")
	private String userName;
	/**
	 * 登录名
	 */
	@TableField(value = "user_account")
	private String userAccount;

	/**
	 * 加盐
	 */
	@TableField(value = "user_salt")
	private String userSalt;

	/**
	 * 用户密码
	 */
	@TableField(value = "user_password")
	private String userPassword;


}
