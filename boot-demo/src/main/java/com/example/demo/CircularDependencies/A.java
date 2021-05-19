package com.example.demo.CircularDependencies;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/5/12 14:34
 * @Version: 1.0
 */
public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
    public A(){
        System.out.println("----A created success");
    }
}
