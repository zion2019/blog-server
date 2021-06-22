package com.huaxing.resource.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huaxing.resource.biz.entity.SysRoleEntity;

import java.util.List;

/**
 * 角色表
 *
 * @email boss
 * @date 2021-04-19 16:47:11
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 查询用户的所有角色ID合集
     * @param userId
     * @return
     */
    List<Long> findRoleIdsByUserId(Long userId);
}

