package com.huaxing.blog.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huaxing.blog.api.vo.BlogCategoryVo;
import com.huaxing.blog.biz.entity.BlogCategoryEntity;
import com.huaxing.framework.api.vo.SelectionVo;
import com.huaxing.framework.core.page.PageDto;
import com.huaxing.framework.core.response.ResponseResult;

import java.util.List;

/**
 * 博文类别
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
public interface BlogCategoryService extends IService<BlogCategoryEntity> {

    ResponseResult<Boolean> saveOrEdit(BlogCategoryVo vo);

    PageDto<BlogCategoryVo> page(BlogCategoryVo vo);

    List<SelectionVo> selection();
}

