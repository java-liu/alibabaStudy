package com.sanshao.offer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: java类 CyclicBarrier
 * @Author: Liuys
 * @CreateDate: 2021/3/31 17:42
 * @Version: 1.0
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //public CyclicBarrier(int parties, Runnable barrierAction) {
        //        if (parties <= 0) throw new IllegalArgumentException();
        //        this.parties = parties;
        //        this.count = parties;
        //        this.barrierCommand = barrierAction;
        //    }
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("******召唤神龙!");
        });
        for(int i = 1; i <= 7 ; i++) {
            final int tempInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t收集到第"+ tempInt + "颗龙珠✪");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
          
        }
    }
}
