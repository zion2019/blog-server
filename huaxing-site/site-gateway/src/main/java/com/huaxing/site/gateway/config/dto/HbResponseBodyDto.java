package com.huaxing.site.gateway.config.dto;

import com.huaxing.site.gateway.constant.SecurityMessageCode;
import lombok.Data;

/**
 * @author Zion
 * @Description security 响应信息载体
 **/
@Data
public class HbResponseBodyDto {

    /**
     * 状态
     */
    private String status;

    /**
     * 信息
     */
    private String msg;

    /**
     * 结果
     */
    private Object result;

    /**
     * token
     */
    private String accessToken;


    public HbResponseBodyDto response(SecurityMessageCode securityMessageCode){
        this.status = securityMessageCode.getCode();
        this.msg = securityMessageCode.getMessage();
        return this;
    }

    public HbResponseBodyDto response(SecurityMessageCode securityMessageCode,String msg){
        this.status = securityMessageCode.getCode();
        this.msg = msg;
        return this;
    }

    public HbResponseBodyDto response(SecurityMessageCode securityMessageCode,Object data){
        this.status = securityMessageCode.getCode();
        this.msg = securityMessageCode.getMessage();
        this.result = data;
        return this;
    }
}
