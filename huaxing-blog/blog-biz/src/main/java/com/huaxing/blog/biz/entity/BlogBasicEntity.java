package com.huaxing.blog.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huaxing.framework.datasource.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 博文基本信息
 * 
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Data
@TableName("blog_basic")
public class BlogBasicEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	@TableField(value = "title")
	private String title;
	/**
	 * 类别ID
	 */
	@TableField(value = "category_id")
	private Long categoryId;
	/**
	 * 封面图片
	 */
	@TableField(value = "cover_img")
	private String coverImg;

}
