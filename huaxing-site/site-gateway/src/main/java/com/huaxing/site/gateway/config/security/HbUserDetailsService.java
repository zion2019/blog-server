package com.huaxing.site.gateway.config.security;

import com.huaxing.site.mesosphere.feign.system.user.SysUserFeign;
import com.huaxing.site.mesosphere.vo.common.BaseResponseVo;
import com.huaxing.site.mesosphere.vo.system.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Zion
 * @Description 用户资源获取
 **/
@Slf4j
@Service
public class HbUserDetailsService implements ReactiveUserDetailsService  {

    @Autowired
    private SysUserFeign sysUserFeign;

    @Override
    public Mono<UserDetails> findByUsername(String userAccount) {

        BaseResponseVo<SysUserVo> findUserResult = sysUserFeign.getUserByAccount(userAccount);
        if(findUserResult == null){
            return Mono.error(new UsernameNotFoundException("find user server fail"));
        }

        SysUserVo sysUser = findUserResult.getData();

        if(sysUser == null){
            return Mono.error(new UsernameNotFoundException("User Not Found"));
        }

        UserDetails user = User.withUsername(sysUser.getUserAccount())
                .password(sysUser.getUserPassword())
                .roles("admin").authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"))
                .build();
        return Mono.just(user);

    }

}

