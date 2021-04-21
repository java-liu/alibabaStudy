package com.sanshao.offer;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockA + "\t 尝试获取："  + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockB + "\t 尝试获取："  + lockA);
            }
        }
    }
}
/**
 * @Description: 死锁
 * @Author: Liuys
 * @CreateDate: 2021/4/8 10:39
 * @Version: 1.0
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB),"Thread-A").start();
        new Thread(new HoldLockThread(lockB, lockA),"Thread-B").start();
        /**
         * linux 下有ps -ef|grep XXXX ls -l
         * windows 下的java运行程序  也有类似ps的查看进程的命令，
         * jps = java ps   jps -l
         */
    }
}
