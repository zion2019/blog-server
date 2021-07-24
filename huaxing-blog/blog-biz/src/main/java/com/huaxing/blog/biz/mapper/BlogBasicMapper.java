package com.huaxing.blog.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huaxing.blog.api.vo.BlogVo;
import com.huaxing.blog.biz.entity.BlogBasicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 博文基本信息
 * 
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-05-13 19:41:17
 */
@Mapper
public interface BlogBasicMapper extends BaseMapper<BlogBasicEntity> {

    /**
     * 博文列表SQL查询
     * @param objectPage
     * @param vo
     * @return
     */
    Page<BlogVo> page(Page<Object> objectPage, @Param("vo") BlogVo vo);
}
