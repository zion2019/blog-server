package com.huaxing.resource.api.feign;

import com.huaxing.framework.core.constant.SecurityConstants;
import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.resource.api.contanst.ResourceContanst;
import com.huaxing.resource.api.dto.SysUserInfo;
import com.huaxing.resource.api.feign.factory.SysUserServiceFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Zion
 * @Description 系统用户服务调用
 **/
@FeignClient(contextId = "sysUserServiceFeign", value = ResourceContanst.SERVER_NAME, fallbackFactory = SysUserServiceFeignFactory.class)
public interface SysUserServiceFeign {

    /**
     * 通过用户名查询用户、角色信息
     * @param account 用户名
     * @param from 调用标志
     * @return R
     */
    @GetMapping("/user/info/{account}")
    ResponseResult<SysUserInfo> info(@PathVariable("account") String account, @RequestHeader(SecurityConstants.FROM) String from);
}
