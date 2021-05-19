package com.example.demo.CircularDependencies;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/5/12 14:37
 * @Version: 1.0
 */
public class ClientCode {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);
    }
}
