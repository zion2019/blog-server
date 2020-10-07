package com.huaxing.site.system.controller.auth;

import com.huaxing.framework.subject.BaseSubject;
import com.huaxing.site.mesosphere.feign.system.auth.AuthFeign;
import com.huaxing.site.mesosphere.vo.common.BaseResponseVo;
import com.huaxing.site.system.service.impl.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zion
 * @Description 鉴权相关
 **/
@RestController
@RequestMapping("auth")
public class AuthController implements AuthFeign {

    @Autowired
    private AuthService authService;

    @Override
    @GetMapping("checkToken")
    public BaseResponseVo<BaseSubject> checkToken(@RequestParam("token") String token) {
        BaseSubject subject = authService.checkToken(token);
        return BaseResponseVo.success().setData(subject);
    }
}
