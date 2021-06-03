package com.huaxing.blog.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.blog.biz.entity.BlogContentEntity;
import com.huaxing.blog.biz.mapper.BlogContentMapper;
import com.huaxing.blog.biz.service.BlogContentService;
import org.springframework.stereotype.Service;

/**
 * 博文内容
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Service("blogContentService")
public class BlogContentServiceImpl extends ServiceImpl<BlogContentMapper, BlogContentEntity> implements BlogContentService {


}