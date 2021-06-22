package com.huaxing.resource.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huaxing.resource.api.dto.SysUserInfo;
import com.huaxing.resource.biz.entity.SysUserEntity;

/**
 * @email boss
 * @date 2021-04-19 16:47:11
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 根据用户账号获取用户信息
     * @param account
     * @return
     */
    SysUserInfo getUserInfoByAccount(String account);

}

