package com.huaxing.resource.api.dto;

import com.huaxing.resource.api.vo.SysUserVo;
import lombok.Data;

/**
 * @author Zion
 * @Description 用户信息汇总
 **/
@Data
public class SysUserInfo {
    /**
     * 用户基本信息
     */
    private SysUserVo sysUser;

    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private Long[] roles;

}
