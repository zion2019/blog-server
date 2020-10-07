package com.huaxing.framework.core.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BaseDto implements Serializable{

	private static final long serialVersionUID = 6439222096469316753L;
	
	private Long id;
	
	private Long tenantId;
	
	private Long createdBy;
	
	private Long createdByApp;
	
	private Date createdAt;
	
	private Long updatedBy;
	
	private Long updatedByApp;
	
	private Date updatedAt;
	
	private Boolean deleted;
	
	private Integer version;

}
