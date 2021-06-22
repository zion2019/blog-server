package com.huaxing.resource.api.feign.fallback;

import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.resource.api.feign.SysDictServiceFeign;
import com.huaxing.resource.api.vo.SysDictItemVo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Zion
 * @Description 字典服务降级
 **/
@Slf4j
@Component
public class SysDictServiceFeignFallbackImpl implements SysDictServiceFeign {
    @Setter
    private Throwable cause;

    @Override
    public ResponseResult<List<SysDictItemVo>> getDictItem(String dictType) {
        log.error("feign 查询字典信息失败:{}", cause.getMessage());
        return ResponseResult.failed();
    }
}
