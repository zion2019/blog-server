package com.huaxing.site.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.site.system.entity.SysRoleEntity;
import com.huaxing.site.system.entity.SysUserRoleEntity;
import com.huaxing.site.system.mapper.SysRoleMapper;
import com.huaxing.site.system.service.SysRoleService;
import com.huaxing.site.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 角色表
 * @email boss
 * @date 2021-04-19 16:47:11
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public List<Long> findRoleIdsByUserId(Long userId) {
        List<Long> roleIdList = new ArrayList<>();
        LambdaQueryWrapper<SysUserRoleEntity> urWrapper = new LambdaQueryWrapper<>();
        urWrapper.eq(SysUserRoleEntity::getUserId,userId).select(SysUserRoleEntity::getRoleId);
        List<SysUserRoleEntity> userRoles = userRoleService.list(urWrapper);
        if(CollectionUtil.isNotEmpty(userRoles)){
            roleIdList.addAll(userRoles.stream().filter(r -> r!=null && r.getRoleId() != null)
                    .map(SysUserRoleEntity::getRoleId).collect(Collectors.toList()));
        }
        return roleIdList;
    }
}