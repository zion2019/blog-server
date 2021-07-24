package com.huaxing.blog.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huaxing.blog.api.vo.BlogVo;
import com.huaxing.blog.biz.entity.BlogBasicEntity;
import com.huaxing.framework.core.page.PageDto;
import com.huaxing.framework.core.response.ResponseResult;

/**
 * 博文基本信息
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
public interface BlogBasicService extends IService<BlogBasicEntity> {

    /**
     * 博文列表分页
     * @param vo
     * @return
     */
    PageDto<BlogVo> pages(BlogVo vo);

    /**
     * 新增or编辑博文
     * @param vo
     * @return
     */
    Boolean saveEditBlog(BlogVo vo);

    /**
     * 删除博文
     * @param id
     * @return
     */
    Boolean removeBlog(Long id);

    /**
     * 博文详细信息
     * @param basicId
     * @return
     */
    BlogVo info(Long basicId);
}

