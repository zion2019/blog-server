package com.huaxing.site.mesosphere.feign.system.auth;

import com.huaxing.framework.subject.BaseSubject;
import com.huaxing.site.mesosphere.vo.common.BaseResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Zion
 * @Description 权限校验对外接口
 **/
@Component
@RequestMapping("auth")
@FeignClient(value = "system-server", contextId = "auth-feign")
public interface AuthFeign {

    /**
     * 校验/解析token
     * @param token
     * @return
     */
    @GetMapping("checkToken")
    BaseResponseVo<BaseSubject> checkToken(@RequestParam("token") String token);
}
