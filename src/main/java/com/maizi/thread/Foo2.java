package com.maizi.thread;

import java.util.concurrent.CountDownLatch;
import java.util.function.IntToDoubleFunction;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.maizi.thread
 * @date 2019/8/2 11:23
 */
public class Foo2 {
    private CountDownLatch l1;
    private CountDownLatch l2;

    public Foo2(){
        l1 = new CountDownLatch(1);
        l2 = new CountDownLatch(1);
    }
    public void first(Runnable printFirst) throws InterruptedException{
        printFirst.run();
        l1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException{
        l1.await();
        printSecond.run();
        l2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException{
        l2.await();
        printThird.run();
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
