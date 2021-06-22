package com.huaxing.resource.api.feign;

import com.huaxing.framework.core.constant.CacheConstants;
import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.resource.api.contanst.ResourceContanst;
import com.huaxing.resource.api.feign.factory.SysDictServiceFeignFactory;
import com.huaxing.resource.api.vo.SysDictItemVo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Zion
 **/
@FeignClient(contextId = "sysDictServiceFeign", value = ResourceContanst.SERVER_NAME, fallbackFactory = SysDictServiceFeignFactory.class)
public interface SysDictServiceFeign {

    @GetMapping("/item")
    @Cacheable(value = CacheConstants.DICT_DETAILS, key = "#dictType")
    ResponseResult<List<SysDictItemVo>> getDictItem(@RequestParam String dictType);

}
