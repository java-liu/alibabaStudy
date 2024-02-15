package com.ljava.somemethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class SomeMethodApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SomeMethodApplication.class, args);
        log.info("----------------");
        Arrays.stream(args).forEach(arg -> {
            log.info("arg: {}", arg);
        });
        /* 命令行参数 */
        log.info("System#getProperty:{}", System.getProperty("demo"));
        log.info("Environment#gerProperty:{}", context.getEnvironment().getProperty("demo"));
    }

}
