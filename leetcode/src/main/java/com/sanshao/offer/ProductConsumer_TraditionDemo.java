package com.sanshao.offer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData//资源类
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() throws Exception
    {
        lock.lock();
        try{
            //1.判断
            //多线程下,不能用if判断
            while(number != 0){
                //等待,不能生产
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.通知唤醒
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void decrement() throws Exception
    {
        lock.lock();
        try{
            //1.判断
            while(number == 0){
                //等待,不能生产
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.通知唤醒
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
/**
 * @Description:
 * 题目:一个初始值为0的变量,两个线程对其交替操作,一个加1一个减1,来5轮
 * (传统生产者,消费者模式)
 *
 * 1.线程 操作资源类
 * 2. 判断  干活  通知
 * 3.防止虚假唤醒机制
 * @Author: Liuys
 * @CreateDate: 2021/4/1 14:16
 * @Version: 1.0
 */
public class ProductConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        //AAA线程生产(也就是执行++)
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AAA").start();

        //BBB线程消费(也就是执行--)
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();
    }
}
