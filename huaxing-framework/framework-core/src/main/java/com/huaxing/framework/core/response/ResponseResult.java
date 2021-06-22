package com.huaxing.framework.core.response;

import com.huaxing.framework.core.exception.HbException;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {
	
	public static final String SUCCESS_CODE ="0000";
	public static final String SUCCESS_MASSAGE ="操作成功";
	public static final String FAIL_CODE ="9999";
	public static final String FAIL_MASSAGE ="操作失败";

	private String code;
	
	private String message;
	
	private T data;

	public ResponseResult() {
		super();
	}

//	public ResponseResult(String code) {
//		super();
//		this.code = code;
//	}
	
	public ResponseResult(HbException e) {
		super();
		this.code = e.getErrorKey();
		this.message = e.getErrorMsg();
	}
	public ResponseResult(T data) {
		super();
		this.code = SUCCESS_CODE;
		this.message = SUCCESS_MASSAGE;
		this.data = data;
	}

	public ResponseResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ResponseResult(String code,String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static <T> ResponseResult<T> ok() {
		return restResult(null, SUCCESS_CODE, null);
	}

	public static <T> ResponseResult<T> ok(T data) {
		return restResult(data, SUCCESS_CODE, null);
	}

	public static <T> ResponseResult<T> ok(T data, String message) {
		return restResult(data, SUCCESS_CODE, message);
	}

	public static <T> ResponseResult<T> failed() {
		return restResult(null, FAIL_CODE, null);
	}

	public static <T> ResponseResult<T> failed(String message) {
		return restResult(null, FAIL_CODE, message);
	}

	public static <T> ResponseResult<T> failed(T data) {
		return restResult(data, FAIL_CODE, null);
	}

	public static <T> ResponseResult<T> failed(T data, String message) {
		return restResult(data, FAIL_CODE, message);
	}

	private static <T> ResponseResult<T> restResult(T data, String code, String message) {
		ResponseResult<T> apiResult = new ResponseResult();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMessage(message);
		return apiResult;
	}

}
