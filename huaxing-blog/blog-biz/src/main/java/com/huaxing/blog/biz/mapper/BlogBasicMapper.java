package com.huaxing.blog.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huaxing.blog.biz.entity.BlogBasicEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 博文基本信息
 * 
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Mapper
public interface BlogBasicMapper extends BaseMapper<BlogBasicEntity> {
	
}
