package com.huaxing.site.gateway.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaxing.site.gateway.config.dto.HbResponseBodyDto;
import com.huaxing.site.gateway.constant.SecurityMessageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Zion
 * @Description 登录失败处理器
 **/
@Slf4j
@Component
public class HbAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {


    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException e) {
        log.error("onAuthenticationFailure {}",e);
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

        HbResponseBodyDto responseBodyDto = new HbResponseBodyDto();
        byte[]   dataBytes={};
        try {
            ObjectMapper mapper = new ObjectMapper();
            dataBytes=mapper.writeValueAsBytes(responseBodyDto.response(SecurityMessageCode.SECURITY_LOGIN_FAIL,e.getMessage()));
        }
        catch (Exception ex){
            log.error("login fail handle mag :",ex);
        }

        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }
}
