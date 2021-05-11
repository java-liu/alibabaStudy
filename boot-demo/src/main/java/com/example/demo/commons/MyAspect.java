package com.example.demo.commons;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description: 切面类
 * @Author: Liuys
 * @CreateDate: 2021/5/10 10:16
 * @Version: 1.0
 */
@Component
@Aspect
public class MyAspect {
    @Before("execution(public int com.example.demo.service.impl.CalcServiceImpl.*(..))")
    public void beforeNotify(){
        System.out.println("******@Before我是前置通知******");
    }
    @After("execution(public int com.example.demo.service.impl.CalcServiceImpl.*(..))")
    public void afterNotify(){
        System.out.println("******@After我是后置通知*******");
    }
    @AfterReturning("execution(public int com.example.demo.service.impl.CalcServiceImpl.*(..))")
    public void afterReturningNotify(){
        System.out.println("******@AfterReturning我是返回后通知*******");
    }
    @AfterThrowing("execution(public int com.example.demo.service.impl.CalcServiceImpl.*(..))")
    public void afterThrowingNotify(){
        System.out.println("******@AfterThrowing我是返回后通知*******");
    }
    @Around("execution(public int com.example.demo.service.impl.CalcServiceImpl.*(..))")
    public Object aroundNotify(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object retValue = null;
        System.out.println("******@Around我是环绕通知之前AAAA******");
        retValue = proceedingJoinPoint.proceed();
        System.out.println("******@Around我是环绕通知之后BBBB******");
        return retValue;
    }

}
