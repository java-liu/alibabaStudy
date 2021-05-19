package com.example.demo;

import com.example.demo.CircularDependencies.A;
import com.example.demo.CircularDependencies.B;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.demo.entity.User;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/3/4 15:42
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args){
        //容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("test.xml");
        User bean = ac.getBean(User.class);
        System.out.println(bean.getName());
        System.out.println(bean.getApplicationContext());
        A a = ac.getBean(A.class);
        B b = ac.getBean(B.class);
        //B b1 = ac.getBean("b1",B.class);


        /***
         * 单例对象的缓存：bean名称-bean实例，即：所谓的单例池
         * 表示已经经历了完整生命周期的Bean对象
         * <b>第一级缓存<b/>
         */
        Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

        /***
         * 早期的单例对象的高速缓存：bean名称-bean实例
         * 表示Bean的生命周期还没走完（Bean的属性还未填充），就把这个Bean存入该缓存中
         * 也就是实例化但未初始化的bean放入该缓存中
         * <b>第二级缓存</b>
         */
        Map<String, Object> earlySingletonObjects = new HashMap<>(16);

        /***
         * 单例工厂的高速缓存：bean名称-ObjectFactory
         * 表示存放生成bean的工厂
         * <b>第三级缓存</b>
         */
        Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);
    }
}
