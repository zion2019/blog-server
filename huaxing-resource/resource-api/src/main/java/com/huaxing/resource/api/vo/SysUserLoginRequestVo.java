package com.huaxing.resource.api.vo;

import lombok.Data;

/**
 * @author Zion
 * @Description 登录请求信息
 **/
@Data
public class SysUserLoginRequestVo {

    private String account;

    private String password;
}
