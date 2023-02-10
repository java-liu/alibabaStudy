package com.ljava;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class IOStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(IOStudyApplication.class, args);
    }
}
