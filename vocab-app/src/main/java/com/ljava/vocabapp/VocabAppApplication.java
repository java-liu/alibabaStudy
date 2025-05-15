package com.ljava.vocabapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ljava.vocabapp.mapper") // 确保扫描 Mapper 接口所在的包
public class VocabAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(VocabAppApplication.class, args);
    }
}
