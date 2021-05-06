package com.huaxing.resource.api.feign.factory;

import com.huaxing.resource.api.feign.SysUserServiceFeign;
import com.huaxing.resource.api.feign.fallback.SysUserServiceFeignFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Zion
 **/
@Component
public class SysUserServiceFeignFactory implements FallbackFactory<SysUserServiceFeign> {

    @Override
    public SysUserServiceFeign create(Throwable throwable) {
        SysUserServiceFeignFallbackImpl remoteLogServiceFallback = new SysUserServiceFeignFallbackImpl();
        remoteLogServiceFallback.setCause(throwable);
        return remoteLogServiceFallback;
    }
}
