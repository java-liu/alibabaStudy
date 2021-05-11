package com.example.demo.service.impl;

import com.example.demo.service.CalcService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @Description: CalcServiceImpl
 * @Author: Liuys
 * @CreateDate: 2021/5/10 10:12
 * @Version: 1.0
 */
@Service
@Profile("dev")
public class CalcServiceImpl implements CalcService {
    @Override
    public int div(int x, int y) {
        int result  = x/y;
        System.out.println("=========CalcServiceImpl被调用了，计算结果"+ result);
        return result;
    }
}
