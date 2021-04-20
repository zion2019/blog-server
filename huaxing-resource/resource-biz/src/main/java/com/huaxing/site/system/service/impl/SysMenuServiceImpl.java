package com.huaxing.site.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.site.system.entity.SysMenuEntity;
import com.huaxing.site.system.mapper.SysMenuMapper;
import com.huaxing.site.system.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统菜单
 * @email boss
 * @date 2021-04-19 16:47:10
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

    @Override
    public List<String> findPermissions(List<Long> roleIds) {
        List<String> permissions = baseMapper.findPermissionByRoleId(roleIds);
        if(CollectionUtil.isNotEmpty(permissions)){
            return permissions.stream().filter(p -> p != null).collect(Collectors.toList());
        }
        return ListUtil.empty();
    }
}