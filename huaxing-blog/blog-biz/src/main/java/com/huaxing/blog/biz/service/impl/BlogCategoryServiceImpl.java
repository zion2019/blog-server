package com.huaxing.blog.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.blog.biz.entity.BlogCategoryEntity;
import com.huaxing.blog.biz.mapper.BlogCategoryMapper;
import com.huaxing.blog.biz.service.BlogCategoryService;
import org.springframework.stereotype.Service;

/**
 * 博文类别
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Service("blogCategoryService")
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategoryEntity> implements BlogCategoryService {


}