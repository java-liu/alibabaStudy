package com.example.demo.service;

import org.springframework.stereotype.Service;

/**
 * @Description: ServiceB,A循环依赖
 * @Author: Liuys
 * @CreateDate: 2021/5/11 11:27
 * @Version: 1.0
 */
@Service
public class ServiceA {
    private ServiceB serviceB;
    public ServiceA(ServiceB serviceB){
        this.serviceB = serviceB;
    }
}
