package com.sanshao.offer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description: java类Semaphore(信号灯)
 * @Author: Liuys
 * @CreateDate: 2021/4/1 9:23
 * @Version: 1.0
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个车位

        for(int i = 1; i <= 6 ; i++) {//模拟6部汽车
            new Thread(()->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +"\t 抢到车位");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
                System.out.println(Thread.currentThread().getName() +"\t 停车3秒后离开车位");
            },String.valueOf(i)).start();

        }
    }
}
