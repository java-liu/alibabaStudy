package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.demo.entity.User;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/3/4 15:42
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext("test.xml");
        User bean = ac.getBean(User.class);
        System.out.println(bean.getName());
        System.out.println(bean.getApplicationContext());
    }
}
