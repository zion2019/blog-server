package com.huaxing.framework.core.page;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {
	
	/**
     * 数据
     */
    private List<T> records = CollUtil.newArrayList();

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
