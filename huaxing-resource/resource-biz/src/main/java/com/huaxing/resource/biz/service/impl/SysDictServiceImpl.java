package com.huaxing.resource.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.framework.core.constant.CacheConstants;
import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.framework.core.utils.Assert;
import com.huaxing.framework.datasource.entity.BaseEntity;
import com.huaxing.resource.api.vo.SysDictItemVo;
import com.huaxing.resource.api.vo.SysDictVo;
import com.huaxing.resource.biz.entity.SysDictEntity;
import com.huaxing.resource.biz.entity.SysDictItemEntity;
import com.huaxing.resource.biz.mapper.SysDictMapper;
import com.huaxing.resource.biz.service.SysDictItemService;
import com.huaxing.resource.biz.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 字典主表
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-06-08 15:28:47
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements SysDictService {

    @Autowired
    private SysDictItemService itemService;

    @Override
    public Boolean addOrEditDict(SysDictVo vo) {

        SysDictEntity dict = null;
        if(vo.getId() != null){
            dict = this.getById(vo.getId());
            Assert.isNull(dict,"dict_error_002");
        }else{
            dict = new SysDictEntity();
        }

        // 名称校验
        Assert.isTrue(this.count(
                new LambdaQueryWrapper<SysDictEntity>().eq(SysDictEntity::getName,vo.getName())
                .ne(vo.getId() != null, BaseEntity::getId,vo.getId())
        ) > 0,"dict_error_003",new String[]{vo.getName()});

        // type校验
        Assert.isTrue(this.count(
                new LambdaQueryWrapper<SysDictEntity>().eq(SysDictEntity::getDictType,vo.getDictType())
                        .ne(vo.getId() != null, BaseEntity::getId,vo.getId())
        ) > 0,"dict_error_004",new String[]{vo.getDictType()});

        dict.setDescription(vo.getDescription());
        dict.setDictType(vo.getDictType());
        dict.setName(vo.getName());
        return this.saveOrUpdate(dict);
    }

    @Override
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    public Boolean addOrEditDictItem(SysDictItemVo vo) {
        SysDictItemEntity dictItem = null;
        if(vo.getId() != null){
            dictItem = itemService.getById(vo.getId());
            Assert.isNull(dictItem,"dict_error_002");
        }else{
            dictItem = new SysDictItemEntity();
        }

        // label校验
        Assert.isTrue(itemService.count(
                new LambdaQueryWrapper<SysDictItemEntity>().eq(SysDictItemEntity::getLabel,vo.getLabel())
                        .ne(vo.getId() != null, BaseEntity::getId,vo.getId())
        ) > 0,"dict_error_003",new String[]{vo.getLabel()});

        BeanUtil.copyProperties(vo,dictItem);
        return itemService.saveOrUpdate(dictItem);
    }


    @Override
    @Cacheable(value = CacheConstants.DICT_DETAILS, key = "#dictType")
    public ResponseResult<List<SysDictItemVo>> getDictItem(String dictType) {
        LambdaQueryWrapper<SysDictItemEntity> qw = new LambdaQueryWrapper<>();
        qw.eq(SysDictItemEntity::getDictType,dictType);
        qw.select(SysDictItemEntity::getDictId,SysDictItemEntity::getDictType
                ,SysDictItemEntity::getLabel,SysDictItemEntity::getSort,SysDictItemEntity::getValue);
        List<SysDictItemEntity> itemList = this.itemService.list(qw);
        if(CollectionUtils.isEmpty(itemList)){
            return ResponseResult.ok();
        }
        return ResponseResult.ok(BeanUtil.copyToList(itemList,SysDictItemVo.class));
    }

}