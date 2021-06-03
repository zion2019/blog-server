package com.huaxing.blog.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huaxing.blog.biz.entity.BlogMessageEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 留言表
 * 
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:18
 */
@Mapper
public interface BlogMessageMapper extends BaseMapper<BlogMessageEntity> {
	
}
