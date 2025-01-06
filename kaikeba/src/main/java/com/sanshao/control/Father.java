package com.sanshao.control;

/**
 * @Description: 父类
 * @Author: Liuys
 * @CreateDate: 2020/6/16 11:24
 * @Version: 1.0
 */
public class Father {
    public void method1(){
        System.out.println("method1......");
    }
    public void method2(){
        System.out.println("method2......");
    }
    public void method3(){
        System.out.println("method3......");
    }

    /***
     * 子类继承父类时，是不能重写父类final修饰的方法。
     * 这就是模板设计模式的简单应用
     */
    public final void executeMethod(){
        method1();
        method2();
        method3();
    }
}
