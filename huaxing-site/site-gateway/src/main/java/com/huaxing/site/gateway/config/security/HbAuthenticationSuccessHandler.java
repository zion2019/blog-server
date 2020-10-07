package com.huaxing.site.gateway.config.security;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaxing.site.gateway.config.dto.HbResponseBodyDto;
import com.huaxing.site.gateway.config.dto.HbUserDetails;
import com.huaxing.site.gateway.constant.SecurityMessageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;

/**
 * @author Zion
 * @Description 登录成功处理器
 **/
@Slf4j
@Component
public class HbAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {


    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();

        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

        HbResponseBodyDto responseBodyDto = new HbResponseBodyDto();
        byte[] dataBytes = {};
        ObjectMapper mapper = new ObjectMapper();
        try {

            User user = (User) authentication.getPrincipal();
            HbUserDetails userDetails = this.buildUser(user);
            byte[] authorization=(userDetails.getUsername()+":"+userDetails.getPassword()).getBytes();
            String token= Base64.getEncoder().encodeToString(authorization);
            httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            dataBytes = mapper.writeValueAsBytes(responseBodyDto.response(SecurityMessageCode.SECURITY_LOGIN_SUCCESS,userDetails));

        } catch (Exception e) {
            log.error("HbAuthenticationSuccessHandler execute error ",e);
            dataBytes = JSONObject.toJSONString(responseBodyDto.response(SecurityMessageCode.SECURITY_LOGIN_FAIL)).getBytes();
        }

        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }



    private HbUserDetails  buildUser(User user){
        HbUserDetails userDetails=new HbUserDetails();
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword().substring(user.getPassword().lastIndexOf("}")+1,user.getPassword().length()));
        return userDetails;
    }

}



