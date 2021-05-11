package com.example.demo.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @ClassName DemobootFilters
 * @Description
 * @Author Lenovo
 * @Date 2019/7/25 14:14
 * @Verson 1.0
 **/
@Configuration
public abstract class DemobootFilters implements WebMvcConfigurer {
    /**
     * 可以直接访问static文件
     * /**  前面不能加static,加了之后不能访问（不知道为啥）
     * @param registry
     */
    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

}
