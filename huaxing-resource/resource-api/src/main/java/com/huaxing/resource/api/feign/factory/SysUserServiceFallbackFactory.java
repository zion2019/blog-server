package com.huaxing.resource.api.feign.factory;

import com.huaxing.resource.api.feign.SysUserServiceClient;
import com.huaxing.resource.api.feign.fallback.SysUserServiceClientFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author Zion
 **/
public class SysUserServiceFallbackFactory  implements FallbackFactory<SysUserServiceClient> {

    @Override
    public SysUserServiceClient create(Throwable throwable) {
        SysUserServiceClientFallbackImpl remoteLogServiceFallback = new SysUserServiceClientFallbackImpl();
        remoteLogServiceFallback.setCause(throwable);
        return remoteLogServiceFallback;
    }
}
