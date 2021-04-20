package com.huaxing.site.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaxing.framework.core.utils.Assert;
import com.huaxing.resource.api.dto.SysUserInfo;
import com.huaxing.resource.api.vo.SysUserVo;
import com.huaxing.site.system.entity.SysUserEntity;
import com.huaxing.site.system.mapper.SysUserMapper;
import com.huaxing.site.system.service.SysMenuService;
import com.huaxing.site.system.service.SysRoleService;
import com.huaxing.site.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @email boss
 * @date 2021-04-19 16:47:11
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysMenuService menuService;

    @Override
    public SysUserInfo getUserInfoByAccount(String account) {
        SysUserInfo sysUserInfo = new SysUserInfo();
        // 用户基本信息
        SysUserEntity userEntity = this.getOne(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getAccount, account));
        Assert.isNull(userEntity,"user_error_001");
        sysUserInfo.setSysUser(BeanUtil.copyProperties(userEntity, SysUserVo.class));

        // 角色
        List<Long> roleIds = roleService.findRoleIdsByUserId(userEntity.getId());
        sysUserInfo.setRoles(ArrayUtil.toArray(roleIds,Long.class));

        // 菜单
        if(CollectionUtil.isNotEmpty(roleIds)){
            List<String> permissions = menuService.findPermissions(roleIds);
            sysUserInfo.setPermissions(ArrayUtil.toArray(permissions,String.class));
        }
        return sysUserInfo;
    }
}