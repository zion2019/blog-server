package com.huaxing.blog.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.blog.biz.entity.BlogBasicEntity;
import com.huaxing.blog.biz.mapper.BlogBasicMapper;
import com.huaxing.blog.biz.service.BlogBasicService;
import org.springframework.stereotype.Service;

/**
 * 博文基本信息
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Service("blogBasicService")
public class BlogBasicServiceImpl extends ServiceImpl<BlogBasicMapper, BlogBasicEntity> implements BlogBasicService {


}