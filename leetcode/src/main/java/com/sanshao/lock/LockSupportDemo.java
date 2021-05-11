package com.sanshao.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: LockSupport类使用
 * @Author: Liuys
 * @CreateDate: 2021/4/30 10:14
 * @Version: 1.0
 */
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread  a = new Thread(() -> {
            try {

                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" +"--------come in" + System.currentTimeMillis());
            LockSupport.park();//wait  被阻塞...等待通知等待放行
            System.out.println(Thread.currentThread().getName() + "\t--------被唤醒  " + System.currentTimeMillis());
        },"A");
        a.start();

        Thread  b = new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t--------发出通知");
        },"B");
        b.start();
    }
}
