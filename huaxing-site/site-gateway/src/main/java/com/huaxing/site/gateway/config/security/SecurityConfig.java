package com.huaxing.site.gateway.config.security;

import com.huaxing.site.gateway.filter.HbAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;


/**
 * @author Zion
 * @Description security配置
 **/
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * 未登录处理器
     */
    @Autowired
    private HbAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 登录成功处理器
     */
    @Autowired
    private HbAuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 登录失败处理器
     */
    @Autowired
    private HbAuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 无权限处理器
     */
    @Autowired
    private HbAccessDeniedHandler accessDeniedHandler;

    /**
     * 无需授权认证列表
     * TODO 这里未实现权限拦截，所有列表中有**匹配所有路径，后期有需要时可做无权限处理逻辑，以及通过security实现用户所配置权限的，拦截
     */
    private static final String[] excludedAuthPages = {
            "/auth/login",
            "/auth/logout",
            "/health",
            "/sys/user/login",
            "/**"
    };

    /**
     * 自定义请求过滤器
     * @return
     */
    @Bean
    public HbAuthenticationFilter customAuthenticationFilter(){
        return new HbAuthenticationFilter();
    }

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {

        http
            .authorizeExchange()
            .pathMatchers(excludedAuthPages).permitAll()  //无需进行权限过滤的请求路径
            .pathMatchers(HttpMethod.OPTIONS).permitAll() //option 请求默认放行
            .anyExchange().authenticated()
            .and()
            .httpBasic()
            .and()
            .formLogin().loginPage("/auth/login")
            .authenticationSuccessHandler(authenticationSuccessHandler) //认证成功
            .authenticationFailureHandler(authenticationFailureHandler) //登陆验证失败
            .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)  //基于http的接口请求鉴权失败
            .and().csrf().disable()//必须支持跨域
            .addFilterAt(customAuthenticationFilter(), SecurityWebFiltersOrder.SECURITY_CONTEXT_SERVER_WEB_EXCHANGE)
            .logout().disable();

        return http.build();
    }
}
