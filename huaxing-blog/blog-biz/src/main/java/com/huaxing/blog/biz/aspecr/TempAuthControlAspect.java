package com.huaxing.blog.biz.aspecr;

import cn.hutool.core.date.DateUtil;
import com.huaxing.framework.core.exception.HbException;
import com.huaxing.framework.core.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class TempAuthControlAspect {

    @Value("${huaxing.blog.admin.account}")
    private String adminAccount;

    @Value("${huaxing.blog.admin.password}")
    private String adminPassword;

    // 以自定义 @WebLog 注解为切点
    @Pointcut("@annotation(com.huaxing.blog.biz.annotation.TempAuthControl)")
    public void auth() {
    }

    /**
     * 切点之前
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("auth()")
    public void before(JoinPoint joinPoint) throws Throwable {
        // 得到 HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String authorization = request.getHeader("Authorization");
        if(StringUtils.isBlank(authorization)){
            throw new HbException(" Authorization is blank");
        }
        String dateFormat = DateUtil.format(new Date(), "yyyy-MM-dd");
        String token = MD5Utils.md5(adminAccount + ":" + adminPassword + ":" + dateFormat);

        if(!authorization.equals(token)){
            throw new HbException(" token is reject");
        }

    }

}
