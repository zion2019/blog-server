package com.huaxing.resource.api.feign.fallback;

import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.resource.api.dto.SysUserInfo;
import com.huaxing.resource.api.feign.SysUserServiceClient;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Zion
 **/
@Slf4j
@Component
public class SysUserServiceClientFallbackImpl implements SysUserServiceClient {

    @Setter
    private Throwable cause;

    @Override
    public ResponseResult<SysUserInfo> info(String account, String from) {
        log.error("feign 查询用户信息失败:{}", account, cause);
        return null;
    }
}
