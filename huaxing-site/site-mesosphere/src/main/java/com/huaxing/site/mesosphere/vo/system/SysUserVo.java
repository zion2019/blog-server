package com.huaxing.site.mesosphere.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Zion
 * @Description 用户信息VO
 **/
@Data
public class SysUserVo implements Serializable {

    private String userId;

    private String userName;

    private String userAccount;

    private String userPassword;

}
