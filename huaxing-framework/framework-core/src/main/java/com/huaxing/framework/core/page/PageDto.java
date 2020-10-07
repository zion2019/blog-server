package com.huaxing.framework.core.page;

import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class PageDto<T> {
	
	/**
     * 数据
     */
    private List<T> records = Collections.emptyList();

    /**
     * 总记录数
     */
    private long total = 0;
    /**
     * 分页数
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;
    
    /**
     * 总页数
     */
    private long pages = 0L;

}
