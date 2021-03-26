package com.sanshao.jmm;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/3/19 10:46
 * @Version: 1.0
 */
public class T1 {
    volatile int n = 0;
    public void add(){
        n++;
    }

    public static void main(String[] args){
        T1 t1 = new T1();
        t1.add();
    }
}
