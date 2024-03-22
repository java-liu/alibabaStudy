package com.ljava;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication(proxyBeanMethods = false)
@Slf4j
public class IOStudyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IOStudyApplication.class, args);
        log.info("----------------");
        Arrays.stream(args).forEach(arg -> {
            log.info("arg: {}", arg);
        });
        /* 命令行参数 */
        log.info("System#getProperty:{}", System.getProperty("demo"));
        log.info("Environment#gerProperty:{}", context.getEnvironment().getProperty("demo"));
    }
}
