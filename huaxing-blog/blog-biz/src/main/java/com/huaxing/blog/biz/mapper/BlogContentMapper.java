package com.huaxing.blog.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huaxing.blog.biz.entity.BlogContentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 博文内容
 * 
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Mapper
public interface BlogContentMapper extends BaseMapper<BlogContentEntity> {
	
}
