package com.huaxing.resource.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huaxing.resource.biz.entity.SysMenuEntity;

import java.util.List;

/**
 * 系统菜单
 * @email boss
 * @date 2021-04-19 16:47:10
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 角色下菜单权限
     * @param roleIds
     * @return
     */
    List<String> findPermissions(List<Long> roleIds);
}

