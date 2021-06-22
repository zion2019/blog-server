package com.huaxing.resource.api.feign.factory;

import com.huaxing.resource.api.feign.SysDictServiceFeign;
import com.huaxing.resource.api.feign.fallback.SysDictServiceFeignFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Zion
 **/
@Component
public class SysDictServiceFeignFactory implements FallbackFactory<SysDictServiceFeign> {

    @Override
    public SysDictServiceFeign create(Throwable throwable) {
        SysDictServiceFeignFallbackImpl remoteLogServiceFallback = new SysDictServiceFeignFallbackImpl();
        remoteLogServiceFallback.setCause(throwable);
        return remoteLogServiceFallback;
    }
}
