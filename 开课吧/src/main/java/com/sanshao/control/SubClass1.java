package com.sanshao.control;

/**
 * @Description: 子类继承父类，如果没有重写父类的方法，而去执行父类中的final方法，没有重写的方法会执行父类中的方法
 * @Author: Liuys
 * @CreateDate: 2020/6/16 11:31
 * @Version: 1.0
 */
public class SubClass1 extends Father {
    @Override
    public void method1() {
        System.out.println("SubClass1的method1......");
    }

    public void method4() {
        System.out.println("SubClass1的method2......");
    }

    @Override
    public void method3() {
        System.out.println("SubClass1的method3......");
    }
}
