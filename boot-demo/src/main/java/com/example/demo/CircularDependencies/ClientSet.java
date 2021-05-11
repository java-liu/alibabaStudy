package com.example.demo.CircularDependencies;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/5/11 14:14
 * @Version: 1.0
 */
public class ClientSet {
    public static void main(String[] args) {
        ServiceAA a = new ServiceAA();
        ServiceBB b = new ServiceBB();
        //将serviceAA注入到ServiceBB中
        b.setServiceAA(a);
        //将ServiceBB注入到ServiceAA中
        a.setServiceBB(b);
    }
}
