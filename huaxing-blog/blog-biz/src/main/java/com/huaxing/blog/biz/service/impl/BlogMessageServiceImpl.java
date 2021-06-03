package com.huaxing.blog.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.blog.biz.entity.BlogMessageEntity;
import com.huaxing.blog.biz.mapper.BlogMessageMapper;
import com.huaxing.blog.biz.service.BlogMessageService;
import org.springframework.stereotype.Service;

/**
 * 留言表
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:18
 */
@Service("blogMessageService")
public class BlogMessageServiceImpl extends ServiceImpl<BlogMessageMapper, BlogMessageEntity> implements BlogMessageService {


}