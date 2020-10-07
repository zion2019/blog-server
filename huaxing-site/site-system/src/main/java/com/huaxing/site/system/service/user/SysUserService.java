package com.huaxing.site.system.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huaxing.site.mesosphere.vo.system.AuthTokenResVo;
import com.huaxing.site.mesosphere.vo.system.SysUserVo;
import com.huaxing.site.system.entity.SysUserEntity;

/**
 * @author Zion
 * @Description 用户基础服务
 **/
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 登录
     * @param request
     * @return
     */
    AuthTokenResVo login(SysUserVo request);

    /**
     *
     * @param request
     * @return
     */
    void register(SysUserVo request);

    SysUserVo getUserByAccount(String userAccount);
}
