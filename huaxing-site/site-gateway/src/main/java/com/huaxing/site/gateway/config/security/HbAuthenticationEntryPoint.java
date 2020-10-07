package com.huaxing.site.gateway.config.security;

import com.alibaba.fastjson.JSONObject;
import com.huaxing.site.gateway.config.dto.HbResponseBodyDto;
import com.huaxing.site.gateway.constant.SecurityMessageCode;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Zion
 * @Description security未登录处理器
 **/
@Component
public class HbAuthenticationEntryPoint extends HttpBasicServerAuthenticationEntryPoint {

    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final String DEFAULT_REALM = "Realm";
    private static String WWW_AUTHENTICATE_FORMAT = "Basic realm=\"%s\"";

    public HbAuthenticationEntryPoint() {

    }

    /**
     * 重写 headerValue 方法
     */
    private String headerValue = createHeaderValue("Realm");

    public void setRealm(String realm) {
        this.headerValue = createHeaderValue(realm);
    }

    private static String createHeaderValue(String realm) {
        Assert.notNull(realm, "realm cannot be null");
        return String.format(WWW_AUTHENTICATE_FORMAT, new Object[]{realm});
    }

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json; charset=UTF-8");
        response.getHeaders().set(HttpHeaders.AUTHORIZATION, this.headerValue);

        HbResponseBodyDto responseBodyDto = new HbResponseBodyDto();
        responseBodyDto.response(SecurityMessageCode.SECURITY_AUTHENTICATION_DENIED);
        byte[] dataBytes = JSONObject.toJSONString(responseBodyDto).getBytes();

        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }
}
