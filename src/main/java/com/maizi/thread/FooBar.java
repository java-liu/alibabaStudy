package com.maizi.thread;



/**
 * 题目：交替打印FooBar
 * @author Liuys
 * @version V1.0
 * @Package com.maizi.thread
 * @date 2019/8/2 14:05
 */
public class FooBar {
    private int n;
    private volatile boolean b = false;
    private Object lock = new Object();
    public FooBar(int n){
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException{
        for(int i = 0;i< n; i++){
            synchronized (lock){
                while (b){
                    lock.wait();
                }
                printFoo.run();
                b = true;
                lock.notifyAll();
            }
        }
    }
    public void bar(Runnable printBar) throws InterruptedException{
        for(int i = 0; i<n; i++){
            synchronized (lock){
                while(!b){
                    lock.wait();
                }
                printBar.run();
                b = false;
                lock.notifyAll();
            }
        }
    }

}
