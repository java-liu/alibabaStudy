package com.sanshao.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/4/30 13:59
 * @Version: 1.0
 */
public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        //带入一个银行办理业务的案例来模拟AQS如何进行线程的管理和通知唤醒机制
        //3个线程模拟3个银行窗口，办理业务
        new Thread(() -> {
            lock.lock();
            try{
                System.out.println("-------A thread come in");
                //暂停几分钟线程
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }
        },"A").start();
        //第二个顾客，第2个线程  -----> 由于受理业务的窗口只有一个（只有一个线程持有锁），此时B只能等待
        new Thread(() -> {
            lock.lock();
            try{
                System.out.println("-------B thread come in");
            }finally {
                lock.unlock();
            }
        },"B").start();

        //第二个顾客，第3个线程  -----> 由于受理业务的窗口只有一个（只有一个线程持有锁），此时C只能等待
        new Thread(() -> {
            lock.lock();
            try{
                System.out.println("-------C thread come in");
            }finally {
                lock.unlock();
            }
        },"C").start();
    }
}
