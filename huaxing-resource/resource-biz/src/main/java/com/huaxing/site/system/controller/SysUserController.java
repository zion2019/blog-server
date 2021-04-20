package com.huaxing.site.system.controller;

import com.huaxing.framework.core.constant.SecurityConstants;
import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.resource.api.dto.SysUserInfo;
import com.huaxing.resource.api.feign.SysUserServiceClient;
import com.huaxing.site.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zion
 * @Description 用户信息管理
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户信息管理")
public class SysUserController implements SysUserServiceClient {

    @Autowired
    private SysUserService userService;

    @Override
    @GetMapping("/user/info/{account}")
    public ResponseResult<SysUserInfo> info(@PathVariable("account")String account, @RequestHeader(SecurityConstants.FROM)String from) {
        return ResponseResult.ok(userService.getUserInfoByAccount(account));
    }
}
