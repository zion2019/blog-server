package com.huaxing.framework.subject;


import java.io.Serializable;
import java.util.List;

/**
 * @author Zion
 * @Description 用户信息
 **/
public class BaseSubject implements Serializable {


    public static final String HEARD_SUBJECT_KEY = "BASE_SUBJECT";

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户角色
     */
    private List<Long> roles;

    public BaseSubject(){

    }
    public BaseSubject(String userId, String userAccount, String userName) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
}
