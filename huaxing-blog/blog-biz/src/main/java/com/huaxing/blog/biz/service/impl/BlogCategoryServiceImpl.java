package com.huaxing.blog.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.blog.api.vo.BlogCategoryVo;
import com.huaxing.blog.biz.entity.BlogCategoryEntity;
import com.huaxing.blog.biz.mapper.BlogCategoryMapper;
import com.huaxing.blog.biz.service.BlogCategoryService;
import com.huaxing.framework.api.vo.SelectionVo;
import com.huaxing.framework.core.page.PageDto;
import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.framework.core.utils.Assert;
import com.huaxing.framework.datasource.entity.BaseEntity;
import com.huaxing.framework.datasource.page.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 博文类别
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Service("blogCategoryService")
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategoryEntity> implements BlogCategoryService {


    @Override
    public ResponseResult<Boolean> saveOrEdit(BlogCategoryVo vo) {
        LambdaQueryWrapper<BlogCategoryEntity> bcWrapper = new LambdaQueryWrapper<>();
        bcWrapper.eq(BlogCategoryEntity::getName,vo.getName());
        bcWrapper.ne(vo.getId() != null, BaseEntity::getId,vo.getId());
        Assert.isTrue(this.count(bcWrapper) > 0,"BLOG_CATEGORY_NAME_EXISTS",new Object[]{vo.getName()});
        BlogCategoryEntity blogCategoryEntity = BeanUtil.copyProperties(vo, BlogCategoryEntity.class);
        return ResponseResult.ok(this.saveOrUpdate(blogCategoryEntity));
    }

    @Override
    public PageDto<BlogCategoryVo> page(BlogCategoryVo vo) {
        Page<BlogCategoryEntity> pages = this.page(new Page(vo.getCurrent(), vo.getSize())
                , new LambdaQueryWrapper<BlogCategoryEntity>()
                        .like(StringUtils.isNotBlank(vo.getName()), BlogCategoryEntity::getName, vo.getName())
                        .like(StringUtils.isNotBlank(vo.getCode()), BlogCategoryEntity::getCode, vo.getCode()));
        return PageUtil.transferPageDto(pages,BlogCategoryVo.class);
    }

    @Override
    public List<SelectionVo> selection() {
        List<SelectionVo> selectionVos = new ArrayList<>();

        List<BlogCategoryEntity> categorys = this.list(new LambdaQueryWrapper<BlogCategoryEntity>().select(BaseEntity::getId, BlogCategoryEntity::getName));
        if(!CollectionUtils.isEmpty(categorys)){
            categorys.forEach(c -> {
                SelectionVo selectionVo = new SelectionVo();
                selectionVo.setValue(c.getId());
                selectionVo.setLabel(c.getName());
                selectionVos.add(selectionVo);
            });
        }

        return selectionVos;
    }
}