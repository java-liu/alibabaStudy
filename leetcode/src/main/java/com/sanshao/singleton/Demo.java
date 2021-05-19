package com.sanshao.singleton;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/5/18 17:18
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        GlobalNum gn = GlobalNum.getGn();
        GlobalNum gn1 = GlobalNum.getGn();
        GlobalNum gn2 = GlobalNum.getGn();
        GlobalNum gn3 = GlobalNum.getGn();
        System.out.println(gn==gn1);
        System.out.println(gn2==gn3);
        new Thread(() -> {
            for(int i = 0;i < 10;i++){
                System.out.println(gn.getNum()+"A");
            }
        },"A").start();
        new Thread(() -> {
            System.out.println(gn.getNum()+"B");
        },"B").start();
    }
}
