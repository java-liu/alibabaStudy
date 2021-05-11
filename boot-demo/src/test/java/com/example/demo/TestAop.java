package com.example.demo;

import com.example.demo.service.CalcService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import javax.annotation.Resource;

/**
 * @Description: aop测试
 * @Author: Liuys
 * @CreateDate: 2021/5/10 14:17
 * @Version: 1.0
 */
@SpringBootTest
public class TestAop {
    @Resource
    private CalcService calcService;

    /***
     * try{
     *     @Around之前
     *     @Before
     *     method.invoke(obj,args);
     *     @Around之后
     *     @AfterReturning
     * }catch(){
     *     @AfterThrowing
     * }finally{
     *     @After
     * }
     * 正常执行：@Before(前置通知)-------@After（后置通知）-------@AfterReturning（正常返回）
     * 异常执行：@Before(前置通知)-------@After(后置通知)--------@AfterThrowing(方法异常)
     */
    @Test
    public void teseAop4(){
        System.out.println("Spring版本是："+ SpringVersion.getVersion() + "\t"+ "SpringBoot版本是：" + SpringBootVersion.getVersion());
        calcService.div(10,2);
    }
    @Test
    public void teseAop5(){
        System.out.println("Spring版本是："+ SpringVersion.getVersion() + "\t"+ "SpringBoot版本是：" + SpringBootVersion.getVersion());
        calcService.div(10,0);
    }
}
