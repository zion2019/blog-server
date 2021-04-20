package com.huaxing.resource.api.vo;

import lombok.Data;

/**
 * @author Zion
 * @Description 用户信息VO
 **/
@Data
public class SysUserVo {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;
    /**
     * 手机号码
     */
    private String telephone;
    /**
     * 账户名称
     */
    private String account;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户盐值
     */
    private String salt;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 是否停用 0:停用 1:有效
     */
    private Integer enabled;


}
