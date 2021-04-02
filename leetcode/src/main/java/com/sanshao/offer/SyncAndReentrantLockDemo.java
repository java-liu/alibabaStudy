package com.sanshao.offer;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource
{
    private int number = 1; //A:1 B:2 C:3 标志位
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    //1 判断
    public void print(int num, int count){
        lock.lock();
        try{
            //第一次,number = 1,c1不需要等待,去干活,
            //干完活,唤醒c2,number=2,此时c1.await()
            //c2唤醒,判断number != 2,c2不等待,c2干活,
            //c2干完活,唤醒c3,number=3,此时c2.await()
            //c3唤醒,判断number != 3,c3不等待,c3干活,
            if(num == 1){
                while( number != 1){
                    c1.await();
                }
            }else if(num == 2){
                while( number != 2){
                    c2.await();
                }
            }else if(num == 3){
                while( number != 3){
                    c3.await();
                }
            }

            for (int j = 1; j <= count; j++) {
                System.out.println(Thread.currentThread().getName() + "\t" + j);
            }
            if(number == 1){
                number = 2;
                //通知下一个线程
                c2.signal();
            }else if(number == 2){
                number = 3;
                //通知下一个线程
                c3.signal();
            }else if(number == 3){
                number = 1;
                //通知下一个线程
                c1.signal();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}

/**
 * @Description: sync和ReentrantLock区别
 * 题目:多线程之间按顺序调用,实现A->B->C三个线程启动,要求如下:
 *  A打印5次,B打印10次,C打印15次
 *  紧接着
 *  A打印5次,B打印10次,C打印15次
 *  ...
 *  来10轮
 * @Author: Liuys
 * @CreateDate: 2021/4/1 16:12
 * @Version: 1.0
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        /*synchronized (new Object()){
            new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                }
            },"A").start();
            TimeUnit.SECONDS.sleep(1);
            new Thread(() -> {
                for (int i = 5; i < 16; i++) {
                    System.out.println(i);
                }
            },"B").start();
            TimeUnit.SECONDS.sleep(1);
            new Thread(() -> {
                for (int i = 17; i < 33; i++) {
                    System.out.println(i);
                }
            },"C").start();
        }*/
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                shareResource.print(1 ,5);
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                shareResource.print(2,10);
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                shareResource.print(3,15);
            }
        },"C").start();
    }
}
