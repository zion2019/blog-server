package com.huaxing.blog.biz.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.huaxing.framework.core.response.ResponseResult;
import com.huaxing.framework.core.utils.MD5Utils;
import com.huaxing.resource.api.vo.SysUserLoginRequestVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class TempLoginController {

    @Value("${huaxing.blog.admin.account}")
    private String adminAccount;

    @Value("${huaxing.blog.admin.password}")
    private String adminPassword;

    /**
     * 临时的登陆接口，服务器资源有限。启不了鉴权服务 - ^_-
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody SysUserLoginRequestVo qo){
        if(!qo.getAccount().equals(adminAccount)){
            return ResponseResult.failed("帐号错误");
        }

        if(!qo.getPassword().equals(adminPassword)){
            return ResponseResult.failed("密码错误");
        }

        String dateFormat = DateUtil.format(new Date(), "yyyy-MM-dd");
        String token = MD5Utils.md5(adminAccount + ":" + adminPassword + ":" + dateFormat);

        Map<String,Object> map = new HashMap<>();
        map.put("userId","1");
        map.put("token",token);
        return ResponseResult.ok(map);
    }
}
