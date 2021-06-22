package com.huaxing.resource.biz.controller;

import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.resource.api.feign.SysDictServiceFeign;
import com.huaxing.resource.api.vo.SysDictItemVo;
import com.huaxing.resource.api.vo.SysDictVo;
import com.huaxing.resource.biz.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Zion
 * @Description 字典模块
 **/
@Api(tags = "字典模块")
@RestController
@RequestMapping("/dict")
public class SysDictController implements SysDictServiceFeign {

    @Autowired
    private SysDictService dictService;

    @PostMapping
    @ApiOperation("新增字典")
//    @PreAuthorize("@pms.hasPermission('sys_dict_add')")
    public ResponseResult<Boolean> addOrEditDict(@RequestBody @Validated SysDictVo vo){
        return ResponseResult.ok(dictService.addOrEditDict(vo));
    }


    @PostMapping("/item")
    @ApiOperation("新增/编辑字典明细")
//    @PreAuthorize("@pms.hasPermission('sys_dict_item_add')")
    public ResponseResult<Boolean> addOrEditDictItem(@RequestBody @Validated SysDictItemVo vo){
        return ResponseResult.ok(dictService.addOrEditDictItem(vo));
    }


    @Override
    @GetMapping("/item")
    @ApiOperation("通过字典类型查找字典")
    public ResponseResult<List<SysDictItemVo>> getDictItem(@ApiParam("dictType") @RequestParam String dictType) {
        return dictService.getDictItem(dictType);
    }


}
