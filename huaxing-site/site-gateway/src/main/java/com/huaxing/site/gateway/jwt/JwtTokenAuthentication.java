package com.huaxing.site.gateway.jwt;

import com.huaxing.framework.subject.BaseSubject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Zion
 * @Description jwt权限信息
 **/
public class JwtTokenAuthentication implements Authentication {

    private BaseSubject subject;

    private Boolean authentication = false;

    public JwtTokenAuthentication(BaseSubject subject, Boolean authentication) {
        this.subject = subject;
        this.authentication = authentication;
    }

    @Override
    public String getName() {

        return subject.getUserAccount();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Long> authorities = subject.getRoles();
        List<SimpleGrantedAuthority> granted = new ArrayList<SimpleGrantedAuthority>();
        for (Long role : authorities) {
            granted.add(new SimpleGrantedAuthority("role_" + role.toString()));
        }
        return granted;
    }

    @Override
    public Object getCredentials() {

        return "";
    }

    @Override
    public Object getDetails() {

        return subject;
    }

    @Override
    public Object getPrincipal() {

        return subject.getUserAccount();
    }

    // 是否已经验证过的
    @Override
    public boolean isAuthenticated() {
        return authentication;
    }

    // 设置是否已经验证过
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authentication = isAuthenticated;
    }
}
