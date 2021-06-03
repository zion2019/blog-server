package com.huaxing.site.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.resource.api.dto.SysUserInfo;
import com.huaxing.resource.api.vo.SysUserLoginRequestVo;
import com.huaxing.site.system.entity.SysUserEntity;

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

