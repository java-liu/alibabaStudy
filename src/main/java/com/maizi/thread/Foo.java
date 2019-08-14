package com.maizi.thread;


/**
 * 题目：按序打印
 * @author Liuys
 * @version V1.0
 * @Package com.maizi.thread
 * @date 2019/8/1 16:56
 */
public class Foo {
    private boolean firstFinished;
    private boolean secondFinished;
    private Object lock = new Object();
    public Foo(){}

    /***
     * 构造屏障来实现，构造2道屏障，second线程等待first屏障，third线程等待second屏障。
     * first线程会释放first屏障，而second 线程会释放 second 屏障。
     * @param printFirst
     * @throws InterruptedException
     */
    public void first(Runnable printFirst)throws InterruptedException{
        synchronized (lock){
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException{
        synchronized (lock){
            while (!firstFinished){
                lock.wait();
            }
            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException{
        synchronized (lock){
            while (!secondFinished){
                lock.wait();
            }
            printThird.run();
        }
    }
    public void one(){
        System.out.println("one");
    }
    public void two(){
        System.out.println("two");
    }
    public void three(){
        System.out.println("three");
    }

}
