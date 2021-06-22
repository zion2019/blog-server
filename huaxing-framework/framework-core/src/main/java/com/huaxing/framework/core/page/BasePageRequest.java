package com.huaxing.framework.core.page;

import lombok.Data;

/**
 * @author Zion
 * @Description 分页请求公共参数
 **/
@Data
public class BasePageRequest {
    /**
     * 分页数
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;

}
