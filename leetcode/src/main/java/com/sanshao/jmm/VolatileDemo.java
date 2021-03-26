package com.sanshao.jmm;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: volatile保证可见性+禁止指令重排
 * 1、验证volatile的可见性
 * 1.1 、假如int number = 0; number 变量之前根本没有添加volatile关键字修饰,没有可见性
 * 1.2 、添加了volatile，可以解决可见性问题
 *
 *
 * 2、验证volatile不保证原子性
 * 2.1原子性指的什么意思？
 *  不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整。要么同时成功，要么同时失败。
 * 2.2 volatile不保证原子性
 *  why
 * 2.3 如果解决原子性
 *  1.加synchronized
 *  2.使用juc(java.util.concurrent)下AtomicInteger
 * @Author: Liuys
 * @CreateDate: 2021/3/11 11:14
 * @Version: 1.0
 */
public class VolatileDemo {
    public static void main(String[] args) {
        //setOkByVolatile();
        MyData myData = new MyData();
        for(int i = 1; i <= 20; i++){
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }
        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值看是多少？
        //这种方式更能保证上面20个线程执行完成之后,再执行main线程
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        //暂停线程一会儿
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value : " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, finally number value : " + myData.atomicInteger);
    }

    //volatile可以保证可见性，及时通知其它线程，主物理内存的值已经被修改。
    private static void setOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "\t come in");
            //暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number value:" + myData.number);
        },"AAA").start();
        //第二个线程是main线程
        while(myData.number == 0){

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over,main Thread get number value:" + myData.number);
    }
}

class MyData{
    volatile int number = 0;
    public void addTo60(){
        this.number = 60;
    }


    //此时number前面是加了volatile
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}
