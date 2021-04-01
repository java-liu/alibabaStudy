package com.sanshao.offer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: java类
 *
 *
 *
 * 1 队列
 * @Author: Liuys
 * @CreateDate: 2021/4/1 10:32
 * @Version: 1.0
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //List list = null;
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
