package com.huaxing.site.system.config;

import com.huaxing.site.system.jwt.JwtProperties;
import com.huaxing.site.system.utils.JwtUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(name = "jwt.enabled", matchIfMissing = true)
public class JwtAutoConfiguration {

	@Bean
	@Primary
	@ConditionalOnMissingBean
	public JwtUtil JwtUtil() {
		return new JwtUtil();
	}

}
