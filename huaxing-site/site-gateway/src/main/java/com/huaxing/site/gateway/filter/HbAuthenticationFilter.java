package com.huaxing.site.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.huaxing.framework.subject.BaseSubject;
import com.huaxing.site.mesosphere.constant.BaseConstant;
import com.huaxing.site.mesosphere.feign.system.auth.AuthFeign;
import com.huaxing.site.gateway.jwt.JwtTokenAuthentication;
import com.huaxing.site.gateway.constant.SecurityMessageCode;
import com.huaxing.site.mesosphere.vo.common.BaseResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Zion
 * @Description 全局路由前置过滤器
 **/
@Slf4j
@Component
public class HbAuthenticationFilter implements WebFilter {

    @Autowired
    private AuthFeign authFeign;

    /**
     * security前置filter
     * @param serverWebExchange
     * @param webFilterChain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        ServerHttpRequest request = serverWebExchange.getRequest();

        // 请求路径
        String requestPath = request.getPath().pathWithinApplication().value();
        log.info("visiting URL ：" + requestPath);
        BaseSubject baseSubject = new BaseSubject();
        baseSubject.setUserAccount("unknown");
        if(Stream.of("/sys/user/login").collect(Collectors.toList()).contains(requestPath)){
            return webFilterChain.filter(serverWebExchange).subscriberContext(ReactiveSecurityContextHolder.withAuthentication(new JwtTokenAuthentication(baseSubject, false)));
        }

        // 请求中获取参数为 Authorization 的参数
        List<String> tokens = request.getHeaders().get("Authorization");
        if(CollectionUtils.isEmpty(tokens) || StringUtils.isBlank(tokens.get(0))){
            return this.writeAuthError(response,"required Authorization in request header !");
        }
        String token = tokens.get(0);

        /**
         * 校验
         *  1. 是否已过期
         *  2. 已注销
         */
        BaseResponseVo<BaseSubject> subjectRsp =  authFeign.checkToken(token);
        if(subjectRsp == null || !subjectRsp.getStatus().equals(BaseConstant.SUCCESS)){
            return this.writeAuthError(response,subjectRsp.getMsg());
        }

        baseSubject = subjectRsp.getData();

        // 当前登录用户信息
//        HttpHeaders headers = request.getHeaders();
//        String s = JSONObject.toJSONString(baseSubject);
//        headers.add(BaseSubject.HEARD_SUBJECT_KEY,s);

        // 绕过spring security 认证
        return webFilterChain.filter(serverWebExchange).subscriberContext(ReactiveSecurityContextHolder.withAuthentication(new JwtTokenAuthentication(baseSubject, false)));
    }

    /**
     * 认证错误输出
     * @param resp 响应对象
     * @param mess 错误信息
     * @return
     */
    private Mono<Void> writeAuthError(ServerHttpResponse resp, String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        BaseResponseVo<String> returnData = new BaseResponseVo<>();
        returnData.setStatus(SecurityMessageCode.SECURITY_ACCESS_DENIED.getCode());
        returnData.setMsg(mess);
        String returnStr = "";
        try {
            returnStr = JSONObject.toJSONString(returnData);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }
}
