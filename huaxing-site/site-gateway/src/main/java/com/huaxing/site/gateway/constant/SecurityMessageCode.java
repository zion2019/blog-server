package com.huaxing.site.gateway.constant;


import java.io.Serializable;

public enum SecurityMessageCode implements Serializable {
    SECURITY_ACCESS_DENIED("300","Need Authorities!"),
    SECURITY_AUTHENTICATION_DENIED("301", "Need Login!"),
    SECURITY_LOGIN_FAIL("400", "Login fail!"),
    SECURITY_LOGIN_SUCCESS("200", "Login Success!");

    //Message 编码
    private String code;
    //Message 描叙
    private String message;

    SecurityMessageCode(String code){
        this.code = code;
    }

    SecurityMessageCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{code:'" + code + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }
}
