package com.ljava.vocabapp.config;

import com.ljava.vocabapp.filter.ContentCachingFilter;
import com.ljava.vocabapp.interceptor.AuthenticationInterceptor;
import com.ljava.vocabapp.interceptor.LoggingInterceptor;
import com.ljava.vocabapp.interceptor.PerformanceMonitorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ContentCachingFilter 被注册为一个全局过滤器，拦截所有请求；
 * LoggingInterceptor 被注册为 Spring MVC 的拦截器，监听所有请求；
 * 二者顺序上，ContentCachingFilter 优先于 LoggingInterceptor 执行，确保日志拦截器能访问到已缓存的请求体。
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;
    @Autowired
    private LoggingInterceptor loggingInterceptor;
    @Autowired
    private PerformanceMonitorInterceptor performanceMonitorInterceptor;

    /**
     * 添加拦截器配置
     * @param registry InterceptorRegistry 实例
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**") // 拦截所有的请求
                .excludePathPatterns("/admin/metrics/api-performance", "/auth/register","/auth/login"); // 排除登录和注册接口
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/**"); // 拦截所有请求进行日志记录
        registry.addInterceptor(performanceMonitorInterceptor)
                .addPathPatterns("/**") // 拦截所有的请求;
                .excludePathPatterns("/auth/login", "/auth/register"); // 排除登录和注册接口
    }

    @Bean
    public FilterRegistrationBean<ContentCachingFilter> contentCachingFilter() {
        FilterRegistrationBean<ContentCachingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ContentCachingFilter());
        registrationBean.addUrlPatterns("/*"); // 拦截所有请求
        registrationBean.setName("contentCachingFilter");
        registrationBean.setOrder(1); // 设置过滤器的执行顺序
        return registrationBean;
    }
}
