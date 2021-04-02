package com.sanshao.offer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource
{
    private volatile boolean FLAG = true; //默认开启, 进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void myProduct() throws Exception{
        String data = null;
        boolean retValue;
        while(FLAG){
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue)
            {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            }else{
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 老板叫停,表示FLAG=false,生产动作结束");
    }

    public void myCustomer() throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() +"\t 超过2秒钟没有取到蛋糕,消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列蛋糕" + result + "成功");
        }
    }

    public void stop() throws Exception{
        this.FLAG = false;
    }
}
/**
 * @Description: 生产 消费者 BlockQueue方式
 *
 *  volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 * @Author: Liuys
 * @CreateDate: 2021/4/2 11:23
 * @Version: 1.0
 */
public class ProductConsumer_BlockQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产者线程启动成功,开始生产蛋糕");
            try {
                myResource.myProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Product").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费者线程启动成功!");
            try {
                myResource.myCustomer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        //5秒钟时间后,大老板main线程叫停
        TimeUnit.SECONDS.sleep(5);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到,大老板main线程叫停,活动结束");
        myResource.stop();
    }

}
