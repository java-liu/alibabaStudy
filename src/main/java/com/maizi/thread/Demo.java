package com.maizi.thread;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.maizi.thread
 * @date 2020/1/19 14:28
 * 创建线程的目的是什么？是为了建立单独的执行路径，让多部分代码实现同时执行。
 * 也就是说线程创建并执行需要给定的代码（线程的任务）
 * 对于主线程，它的任务定义在main函数中。
 * 自定义线程需要执行的任务都定义在run方法中。
 * Thread类中的run方法内部的任务并不是我们所需要的,只要重写这个run方法，
 * 既然Thread类已经定义了线程任务的位置，只要在位置中定义任务代码即可。
 * 所以进行了重写run方法动作。
 *
 *
 * 多线程执行时，在栈内存中，其实每一个执行线程都有一片自己所属的栈内存空间。
 * 进行方法的压栈和弹栈。
 *
 * 当执行线程的任务结束了，线程自动在栈内存中释放了，
 * 但是当所有的执行线程都结束了，进程就结束了。
 *
 * 获取线程名称：
 * Thread.currentThread().getName();
 * 主线程的名称：main
 */
public class Demo extends Thread {
    private String name;
    Demo(String name){
        this.name = name;
    }
    public void run(){
        for(int i= 0; i< 20;i++){
            System.out.println("name= " + name+"...." + i);
        }
    }
}

/***
 * 线程对象调用run方法和调用start方法区别：
 * 调用run方法不开启线程
 * 调用start开启线程，并让jvm调用run方法在开启的线程中执行。
 * 调用start方法，开启线程并让线程执行，同时还会告诉jvm去调用run方法。
 */
class ThreadDemo{
    public static void main(String[] args){
        //创建了两个线程对象
        Demo d1 = new Demo("小强");
        Demo d2 = new Demo("旺财");
        d2.start();//将d2这个线程开启
        d1.run();//由主线程负责
    }
}
