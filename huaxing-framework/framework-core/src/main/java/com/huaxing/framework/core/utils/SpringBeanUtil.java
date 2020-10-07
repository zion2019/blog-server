package com.huaxing.framework.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringBeanUtil继承自ApplicationContextAware，
 * 来获取applicationContext上下文
 * spring提供的后三种方法可以实现在普通的类中继承或实现相应的类或接口来获取spring 的ApplicationContext对象，
 * 但是在使用是一定要注意实现了这些类或接口的普通java类一定要在Spring 的
 * 配置文件applicationContext.xml文件中进行配置。否则获取的ApplicationContext对象将为null。
 * 在application-core.xml中将SpringBeanUtil配置
 */

@Component
public class SpringBeanUtil implements ApplicationContextAware, DisposableBean {
	private static ApplicationContext applicationContext = null;
	private static Logger logger = LoggerFactory
			.getLogger(SpringBeanUtil.class);

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		logger.debug("注入ApplicationContext到SpringContextHolder:"
				+ applicationContext);
		if (SpringBeanUtil.applicationContext != null) {
			logger.warn("SpringContextHolder中的ApplicationContext被覆�?, 原有ApplicationContext�?:"
					+ SpringBeanUtil.applicationContext);
		}

		SpringBeanUtil.applicationContext = applicationContext;
	}

	@Override
	public void destroy() throws Exception {
		clear();
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) (applicationContext == null ? null : applicationContext
				.getBean(name));
	}

	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext == null ? null : applicationContext
				.getBean(requiredType);
	}

	public static void clear() {
		logger.debug("清除SpringContextHolder中的ApplicationContext:"
				+ applicationContext);
		applicationContext = null;
	}
	
}