package com.example.demo.service;

import org.springframework.stereotype.Service;

/**
 * @Description: ServiceA,B循环依赖
 * @Author: Liuys
 * @CreateDate: 2021/5/11 11:27
 * @Version: 1.0
 */
@Service
public class ServiceB {
    private ServiceA serviceA;
    public ServiceB(ServiceA serviceA){
        this.serviceA = serviceA;
    }
}
