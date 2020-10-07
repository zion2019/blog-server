package com.huaxing.site.system.service.impl.auth;

import com.alibaba.fastjson.JSONObject;
import com.huaxing.framework.cache.config.RedisMasterUtil;
import com.huaxing.framework.subject.BaseSubject;
import com.huaxing.framework.core.exception.HbException;
import com.huaxing.framework.core.utils.Assert;
import com.huaxing.framework.core.utils.DateUtil;
import com.huaxing.site.mesosphere.vo.system.AuthTokenResVo;
import com.huaxing.site.mesosphere.vo.system.SysUserVo;
import com.huaxing.site.system.jwt.JwtProperties;
import com.huaxing.site.system.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Zion
 * @Description 认证服务
 **/
@Slf4j
@Service
public class AuthService {

    @Autowired
    private RedisMasterUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 授权码key
     */
    public static final String GRANT_ACCESS_TOKEN = "_ACCESS_TOKEN";
    public static final String GRANT_REFRESH_TOKEN = "_REFRESH_TOKEN";

    /**
     * 生成token
     * @return
     */
    public AuthTokenResVo buildJwtToken(SysUserVo userVo) {
        AuthTokenResVo result = new AuthTokenResVo();

        try{

            // 旧有TOKEN
            String successTokenKey = userVo.getUserAccount() + GRANT_ACCESS_TOKEN;
            String refreshTokenKey = userVo.getUserAccount() + GRANT_REFRESH_TOKEN;
            redisUtil.remove(successTokenKey,refreshTokenKey);

            // 获取jwt配置
            JwtProperties jwtProperties = jwtUtil.getProperties();
            Assert.isTrue(jwtProperties == null,"auth_error_001");

            // subject
            BaseSubject subject = new BaseSubject(userVo.getUserId(),userVo.getUserAccount(),userVo.getUserName());

            // accessToken
            log.info("正在写入授权缓存："+successTokenKey);
            String accessToken = jwtUtil.buildJWT(JSONObject.toJSONString(subject));
            redisUtil.set(successTokenKey, accessToken, jwtProperties.getExpire());

            // refreshToken
            log.info("正在写入刷新缓存:"+refreshTokenKey);
            String refreshToken = jwtUtil.buildJWT(JSONObject.toJSONString(subject),jwtProperties.getRefreshExpire());
            redisUtil.set(refreshTokenKey, refreshToken, jwtProperties.getRefreshExpire());

            result.setUserId(userVo.getUserId());
            result.setUserName(userVo.getUserName());
            result.setAccessToken(accessToken);
            result.setRefreshToken(refreshToken);

            // accessToken失效时间
            Date expiresTime = DateUtil.minute((int) (jwtProperties.getExpire() / 1000 / 60));
            String expiresIn = DateUtil.format(expiresTime);
            result.setExpiresIn(expiresIn);

        }catch (Exception e){
            log.error("token build fail",e);
            throw new HbException("token build fail :",e.getMessage());
        }

        return result;
    }

    /**
     * 校验/解析TOKEN
     * @return
     */
    public BaseSubject checkToken(String token) {
        log.info("check and parse token:{}",token);

        // 是否过期
        Assert.isTrue(!jwtUtil.checkJWT(token),"auth_error_002");

        // 解析
        String parseSubjectStr = jwtUtil.parseSubject(token);
        BaseSubject parseSubject = JSONObject.parseObject(parseSubjectStr, BaseSubject.class);
        String accessTokenKey = parseSubject.getUserAccount() + GRANT_ACCESS_TOKEN;

        // 缓存中取
        String redisToken = (String) redisUtil.get(accessTokenKey);
        Assert.isTrue(StringUtils.isBlank(redisToken),"auth_error_003");

        // 两边对不上，说明已重新登录
        Assert.isTrue(!redisToken.equals(token),"auth_error_003");

        return parseSubject;

    }
}
