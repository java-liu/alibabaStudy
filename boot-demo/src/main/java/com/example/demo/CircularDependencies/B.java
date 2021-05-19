package com.example.demo.CircularDependencies;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/5/12 14:34
 * @Version: 1.0
 */
public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B(){
        System.out.println("----B created success");
    }
}
