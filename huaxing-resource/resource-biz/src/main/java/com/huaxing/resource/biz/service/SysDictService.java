package com.huaxing.resource.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.resource.api.vo.SysDictItemVo;
import com.huaxing.resource.api.vo.SysDictVo;
import com.huaxing.resource.biz.entity.SysDictEntity;

import java.util.List;

/**
 * 字典主表
 *
 * @author zion
 * @email zhou.hx_sr@neusiri.com
 * @date 2021-06-08 15:28:47
 */
public interface SysDictService extends IService<SysDictEntity> {

    /**
     * 根据type查询item信息
     * @param dictType
     * @return
     */
    ResponseResult<List<SysDictItemVo>> getDictItem(String dictType);

    /**
     * 新增编辑字典主项
     * @param vo
     * @return
     */
    Boolean addOrEditDict(SysDictVo vo);

    /**
     * 新增编辑字典明细项
     * @param vo
     * @return
     */
    Boolean addOrEditDictItem(SysDictItemVo vo);
}

