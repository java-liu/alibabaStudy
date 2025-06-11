package com.ljava.vocabapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan("com.ljava.vocabapp.mapper") // 确保扫描 Mapper 接口所在的包
@EnableMethodSecurity  //如果使用了 @PreAuthorize 或 @Secured 注解，请确保加上此注解, 启用方法级别权限控制
public class VocabAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(VocabAppApplication.class, args);
    }
}
