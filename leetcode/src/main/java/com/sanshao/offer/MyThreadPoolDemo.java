package com.sanshao.offer;


import java.util.concurrent.*;

/**
 * @Description: 第四种获得/使用java多线程的方式,线程池
 * @Author: Liuys
 * @CreateDate: 2021/4/6 14:50
 * @Version: 1.0
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //threadPoolInit();
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        //模拟10个用户线程来办理业务,每个用户就是一个来自外部的请求线程.
        try{
            for (int i = 1; i <=10 ; i++) {
                threadPool.execute( ()->{
                    System.out.println(Thread.currentThread().getName() + "\t 处理业务");
                });
                //TimeUnit.MILLISECONDS.sleep(200);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit() {
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池多个处理线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池一个处理线程
        //模拟10个用户线程来办理业务,每个用户就是一个来自外部的请求线程.
        try{
            for (int i = 1; i <=10 ; i++) {
                threadPool.execute( ()->{
                    System.out.println(Thread.currentThread().getName() + "\t 处理业务");
                });
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
