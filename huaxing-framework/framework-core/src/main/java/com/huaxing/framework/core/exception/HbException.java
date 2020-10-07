package com.huaxing.framework.core.exception;

public class HbException extends RuntimeException {
	
	private static final long serialVersionUID = -7096163594400421661L;
	
	private String errorKey;
	private String errorMsg;
	private Object[] params;
	
	public HbException(){}
	
	public HbException(Exception e) {
		super(e);
	}

	public HbException(String errorMsg){
		super(errorMsg);
		this.errorKey ="9999";
		this.errorMsg = errorMsg;
	}

	public HbException(String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorMsg = errorMsg;
	}

	public HbException(String errorKey, String errorMsg) {
		super(errorMsg);
		this.errorKey = errorKey;
		this.errorMsg = errorMsg;
	}

	public HbException(String errorKey, String errorMsg, Throwable cause) {
		super(errorKey, cause);
		this.errorKey = errorKey;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorKey() {
		return errorKey;
	}
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}

}
