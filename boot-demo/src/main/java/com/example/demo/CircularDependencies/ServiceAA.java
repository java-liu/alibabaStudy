package com.example.demo.CircularDependencies;

import org.springframework.stereotype.Component;

/**
 * @Description: 循环依赖测试之set注入方式
 * @Author: Liuys
 * @CreateDate: 2021/5/11 14:10
 * @Version: 1.0
 */
@Component
public class ServiceAA {
    private ServiceBB serviceBB;
    public void setServiceBB(ServiceBB serviceBB){
        this.serviceBB = serviceBB;
        System.out.println("AA里面设置了BB");
    }
}
