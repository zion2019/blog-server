package com.huaxing.site.gateway.config.security;

import com.alibaba.fastjson.JSON;
import com.huaxing.site.gateway.config.dto.HbResponseBodyDto;
import com.huaxing.site.gateway.constant.SecurityMessageCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zion
 * @Description security 无权限处理器
 **/
@Component
public class HbAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        HbResponseBodyDto responseBody = new HbResponseBodyDto();
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody.response(SecurityMessageCode.SECURITY_ACCESS_DENIED)));
    }
}
