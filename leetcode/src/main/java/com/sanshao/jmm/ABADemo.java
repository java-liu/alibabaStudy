package com.sanshao.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description: ABA问题的解决   AtomicStampedReference
 * @Author: Liuys
 * @CreateDate: 2021/3/23 10:24
 * @Version: 1.0
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args){
        //线程t1先把100改成101,后又把101改成100,对于t2线程,拿到的结果值100好像没有改变,但是100已经经过100->101->100的变化过程,(ABA)
        new Thread(()->{
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        },"t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2021) + "\t" + atomicReference.get());
        },"t2").start();
        //暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //以下是ABA问题的解决
        System.out.println("===============以下是ABA问题的解决================");
        //如果仅靠值比较,无法解决这种问题
        new Thread(()->{
            //初始版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第我1次版本号" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //解决ABA问题,添加版本号控制,第一次值=100,版本号1
            //值修改成101,版本号2
            //值又被修改成100,版本号3
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第我2次版本号" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第我3次版本号" + atomicStampedReference.getStamp());
            },"t3").start();

        //t4线程去拿最新值的时候,会比对自己的版本号和实际版本号,同时对比期望值与当前值
        // expectedReference == current.reference &&
        // expectedStamp == current.stamp &&
        new Thread(()->{
            //初始版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第我一次版本号" + stamp);
            //暂停2秒钟t4线程,保证上面的t3线程完成了一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //第三个参数 expectedStamp,期望的版本号,与当前实际版本号对比,如果比对成功,版本号+1,比较不成功,说明已经被其他线程修改,会获取到实际最新的版本号和值.
            boolean result = atomicStampedReference.compareAndSet(100, 2021, stamp, stamp + 1);
            atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t修改成功否:" + result +"\t当前最新实际版本号:" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "\t当前最新实际值:" + atomicStampedReference.getReference());
            },"t4").start();
    }
}
