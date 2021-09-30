package com.huaxing.blog.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.blog.api.vo.BlogVo;
import com.huaxing.blog.biz.entity.BlogBasicEntity;
import com.huaxing.blog.biz.entity.BlogContentEntity;
import com.huaxing.blog.biz.mapper.BlogBasicMapper;
import com.huaxing.blog.biz.service.BlogBasicService;
import com.huaxing.blog.biz.service.BlogContentService;
import com.huaxing.framework.core.page.PageDto;
import com.huaxing.framework.core.response.BaseAttachmentVo;
import com.huaxing.framework.core.utils.Assert;
import com.huaxing.framework.datasource.entity.BaseEntity;
import com.huaxing.framework.datasource.page.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 博文基本信息
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Service("blogBasicService")
public class BlogBasicServiceImpl extends ServiceImpl<BlogBasicMapper, BlogBasicEntity> implements BlogBasicService {

    @Autowired
    private BlogContentService contentService;

    @Override
    public PageDto<BlogVo> pages(BlogVo vo) {
        Page<BlogVo> pages = baseMapper.page(new Page<>(vo.getCurrent(),vo.getSize()),vo);

        pages.getRecords().forEach(b -> {
            b.setMessageCount(RandomUtil.randomInt(100));
            b.setViewCount(RandomUtil.randomInt(30));
        });

        return PageUtil.transferPageDto(pages,BlogVo.class);
    }

    @Override
    public Boolean saveEditBlog(BlogVo vo) {
        BlogBasicEntity basic = null;
        BlogContentEntity content = null;

        if(vo.getId() != null){
            basic = this.getById(vo.getId());
            Assert.isNull(basic,"BLOG_IS_EXISTS",new Object[]{vo.getId()});
            content = contentService.getOne(new LambdaQueryWrapper<BlogContentEntity>()
                    .eq(BlogContentEntity::getBlogId,basic.getId()));
            Assert.isNull(content,"BLOG_CONTENT_IS_EXISTS",new Object[]{basic.getId()});
        }else{
            basic = new BlogBasicEntity();
            content = new BlogContentEntity();
        }

        BeanUtil.copyProperties(vo,basic);
        this.saveOrUpdate(basic);

        content.setBlogContent(vo.getContent());
        content.setBlogId(basic.getId());
        contentService.saveOrUpdate(content);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeBlog(Long id) {
        contentService.remove(new LambdaQueryWrapper<BlogContentEntity>()
                .eq(BlogContentEntity::getBlogId,id));
        this.removeById(id);
        return true;
    }

    @Override
    public BlogVo info(Long basicId) {
        BlogVo vo = new BlogVo();
        BlogBasicEntity basicEntity = this.getById(basicId);
        Assert.isNull(basicEntity,"BLOG_IS_EXISTS",new Object[]{vo.getId()});
        BeanUtil.copyProperties(basicEntity,vo);
        BlogContentEntity content = contentService.getOne(new LambdaQueryWrapper<BlogContentEntity>()
                .eq(BlogContentEntity::getBlogId, basicId).select(BlogContentEntity::getBlogContent));
        Assert.isNull(content,"BLOG_CONTENT_IS_EXISTS",new Object[]{basicId});
        vo.setContent(content.getBlogContent());
        return vo;
    }

    @Override
    public Boolean release(Long basicId,Integer releaseStatus) {
        LambdaUpdateWrapper<BlogBasicEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(BaseEntity::getId,basicId).set(BlogBasicEntity::getRelease,releaseStatus);
        this.update(wrapper);
        return true;
    }
}