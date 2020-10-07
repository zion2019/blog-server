package com.huaxing.site.mesosphere.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Zion
 * @Description 用户登录响应VO
 **/
@Data
public class AuthTokenResVo  implements Serializable {

    /**
     * 授权token
     */
    private String accessToken;

    /**
     * token类型
     */
    private String tokenType = "Bearer";

    /**
     * 刷新token
     */
    private String refreshToken;

    /**
     * 授权token过期时间
     */
    private String expiresIn;

    /**
     * token使用范围
     */
    private String scope = "read";

    /***
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

}
