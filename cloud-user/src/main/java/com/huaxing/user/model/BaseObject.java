package com.huaxing.user.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class BaseObject implements Serializable {

    private String status;

    private String createUser;

    private String updateUser;

    private Date createTime;

    private Date updateTime;

}
