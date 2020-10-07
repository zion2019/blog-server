package com.huaxing.site.mesosphere.vo.common;

import com.huaxing.site.mesosphere.constant.BaseConstant;
import lombok.Getter;

import java.io.Serializable;

/**
 *
 * @param <T>
 */
@Getter
public class BaseResponseVo<T> implements Serializable {

    private static final long serialVersionUID = -4308759780635946860L;

    private static final String SUCCESS = "success";

    private String status;

    private String msg;

    private String error;

    private T data;

    public BaseResponseVo() {

    }

    public BaseResponseVo(String status) {
        this.status = status;
    }

    public BaseResponseVo(String status,String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BaseResponseVo(String status,String msg,String error) {
        this.status = status;
        this.msg = msg;
        this.error = error;
    }

    public BaseResponseVo(String status,String msg,String error,T data) {
        this.status = status;
        this.msg = msg;
        this.error = error;
        this.data = data;
    }

    public BaseResponseVo<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public BaseResponseVo<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public BaseResponseVo<T> setError(String error) {
        this.error = error;
        return this;
    }

    public BaseResponseVo<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static BaseResponseVo success() {
        return new BaseResponseVo(BaseConstant.SUCCESS,SUCCESS);
    }

}
