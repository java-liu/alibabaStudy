package com.sanshao.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 使用synchronized,wait,
 * 要求：t1线程等待3秒钟，3秒后t2线程唤醒t1线程继续工作
 * 以下异常情况：
 * 2 wait方法和notify方法，两个都去掉同步代码块后看运行效果
 * 异常情况：
 * Exception in thread "A" Exception in thread "B" java.lang.IllegalMonitorStateException
 * 	at java.lang.Object.wait(Native Method)
 * java.lang.IllegalMonitorStateException
 * 	at java.lang.Object.notify(Native Method)
 * 	2.2结论
 * 	Object类中的wait、notify、notifyAll用于线程等待和唤醒方法，都必须在synchronized内部执行（必须用到关键字synchronized）
 * @Author: Liuys
 * @CreateDate: 2021/4/28 17:23
 * @Version: 1.0
 */
public class SynchronizedDemo {
    static Object objectLock = new Object();//同一把锁，类似资源类
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static void main(String[] args) {
        //synchronizedWaitNotify();
        new Thread(() -> {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + "\t" +"--------come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t--------被唤醒");
            }finally {
                lock.unlock();
            }
        },"A").start();
        new Thread(() -> {
            lock.lock();
            try{
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t--------通知");
            }finally {
                lock.unlock();
            }
        },"B").start();
    }

    private static void synchronizedWaitNotify() {
        new Thread(() -> {
            synchronized (objectLock){
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t--------come in");
                try{
                    objectLock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t--------被唤醒");
            }
        },"A").start();

        new Thread(() -> {
            synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t--------通知");
            }
        },"B").start();
    }
}
