package com.huaxing.user.model;

import lombok.Data;

/**
 * t_user
 * @author 
 */

@Data
public class User extends BaseObject{
    private Long userId;

    private String userName;

    private String userMobile;

    private String userStatus;

    private String userNickName;

    private String userPassWord;

    private String status;

}