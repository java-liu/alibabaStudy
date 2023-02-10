package com.example.redisstudy.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 *
 *
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;


	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ApplicationContextUtil.context = context;
	}

	/**
	 * 【】：获取当前环境
	 * @param
	 * @return: java.lang.String
	 */
	public static String getActiveProfile() {
		return context.getEnvironment().getActiveProfiles()[0];
	}


	public static ApplicationContext getContext() {
		return context;
	}


	public static <T> T  getApplicationBean(Class<T> clz){
		return ApplicationContextUtil.getContext().getBean(clz);
	}

	/**
	 * 【】：获取配置文件中的资源
	 */
	public static String getProperties(String propKey){
		return ApplicationContextUtil.getContext().getEnvironment().getProperty(propKey);
	}

	/**
	 * 发布事件
	 *
	 * @param event
	 */
	public static void publishEvent(ApplicationEvent event) {
		if (context == null) {
			return;
		}
		context.publishEvent(event);
	}

}
