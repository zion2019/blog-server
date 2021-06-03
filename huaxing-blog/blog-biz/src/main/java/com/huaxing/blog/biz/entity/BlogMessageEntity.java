package com.huaxing.blog.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 留言表
 * 
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:18
 */
@Data
@TableName("blog_message")
public class BlogMessageEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关联业务ID
	 */
	@TableField(value = "business_id")
	private Long businessId;
	/**
	 * 关联业务类型
	 */
	@TableField(value = "business_type")
	private Integer businessType;
	/**
	 * 留言内容
	 */
	@TableField(value = "message_content")
	private String messageContent;
	/**
	 * 留言人昵称
	 */
	@TableField(value = "nick_name")
	private String nickName;


}
