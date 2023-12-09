package com.sanshao.offer;

import com.google.common.base.Stopwatch;

import java.net.URI;
import java.sql.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: java类 CountDownLatch
 * @Author: Liuys
 * @CreateDate: 2021/3/31 16:18
 * @Version: 1.0
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //closeDoor();
        //countDownLatchDemo1();
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        countDownLatchDemo.countDownLatchDemo2();
        //CompletableFutureDemo1();
        List<String> list = new ArrayList<>();
        list.add("1663430327305015298");
        if(!list.contains("1663430327305015298")){
            System.out.println("====");
        }
        System.currentTimeMillis();
    }

    /**
     * 前面所有都执行完,最后执行main线程
     * @throws InterruptedException
     */
    private static void closeDoor() throws InterruptedException {
        //定义一个线程池
        //ExecutorService executor = Executors.newFixedThreadPool(10);
        CustomThreadPool mypool = new CustomThreadPool();
        ThreadPoolExecutor threadPool = mypool.createPool("收集视频");
        //CountDownLatch countDownLatch = new CountDownLatch(6);
        List<String> list = new ArrayList<>();
        for(int i = 1; i <= 6 ; i++) {
            /*new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 国被灭!");
                countDownLatch.countDown();
            },String.valueOf(CountryEnum.forEach_countryEnum(i).getCountry())).start();*/

            int finalI = i;
            CompletableFuture<String> futureResult = CompletableFuture.supplyAsync(() -> {
                //Thread.sleep(2000);
                //countDownLatch.countDown();
                return "hello result" + String.valueOf(finalI);
            }, threadPool);
            try {
                String s = futureResult.get();
                list.add(s);
                System.out.println("result========="+s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        list.forEach(System.out::println);
        //获取执行结果
        //countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦国,一统华夏!");
        //System.out.println(CountryEnum.ONE);
        //System.out.println(CountryEnum.ONE.getCode());
        //System.out.println(CountryEnum.ONE.getCountry());
        //System.out.println(CountryEnum.ONE.getAge());
        mypool.shutdown();
    }

    /**
     * countDownLatch方式一:无返回值
     * @throws InterruptedException
     */
    private static void countDownLatchDemo1() throws InterruptedException {
        //定义一个线程池
        CustomThreadPool mypool = new CustomThreadPool();
        ThreadPoolExecutor threadPool = mypool.createPool("收集视频");
        Stopwatch stopwatch = Stopwatch.createStarted();
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i < 7; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(String.valueOf(CountryEnum.forEach_countryEnum(finalI).getCountry()) + "\t 国被灭!");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await(1200, TimeUnit.MILLISECONDS);
        stopwatch.stop();
        String string = stopwatch.toString();
        System.out.println(string);
        System.out.println(Thread.currentThread().getName() + "\t 秦国,一统华夏!");
        mypool.shutdown();

        //对比不用countDownLatch
        stopwatch.start();
        for (int i = 1; i < 7; i++) {
            int finalI = i;
            Thread.sleep(1000);
            System.out.println(String.valueOf(CountryEnum.forEach_countryEnum(finalI).getCountry()) + "\t 国被灭!");
            countDownLatch.countDown();

        }
        stopwatch.stop();
        String string1 = stopwatch.toString();
        System.out.println(string1);
        /**
         * 收集视频线程池初始化完成
         * 齐	 国被灭!
         * 韩	 国被灭!
         * 赵	 国被灭!
         * 魏	 国被灭!
         * 燕	 国被灭!
         * 楚	 国被灭!
         * 1.025 s
         * main	 秦国,一统华夏!
         * 收集视频执行结束，关闭线程池
         * 齐	 国被灭!
         * 楚	 国被灭!
         * 燕	 国被灭!
         * 赵	 国被灭!
         * 魏	 国被灭!
         * 韩	 国被灭!
         * 7.055 s
         * 使用countDownLatch线程并行执行,所有任务都执行完成之后,不再阻塞主线程,耗时短
         * 不使用countDownLatch,任务顺序执行,总耗时为所有任务的时间,耗时长
         */
    }

    /**
     * countDownLatch方式二:有返回值
     * @throws InterruptedException
     */
    private void countDownLatchDemo2() throws InterruptedException {
        //定义一个线程池
        CustomThreadPool mypool = new CustomThreadPool();
        ThreadPoolExecutor threadPool = mypool.createPool("收集视频");
        Stopwatch stopwatch = Stopwatch.createStarted();
        CountDownLatch countDownLatch = new CountDownLatch(6);
        List<String> list = new ArrayList<>(6);
        for (int i = 1; i < 7; i++) {
            FutureTask<String> futureTask = new FutureTask<>(new getAwayAudioAndVideoTask(countDownLatch, i));
            threadPool.submit(futureTask);
            try {
                list.add(futureTask.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        countDownLatch.await(1200, TimeUnit.MILLISECONDS);
        stopwatch.stop();
        String string = stopwatch.toString();
        System.out.println(string);
        list.forEach(System.out::println);
        System.out.println(Thread.currentThread().getName() + "\t 秦国,一统华夏!");
        mypool.shutdown();
    }

    public class getAwayAudioAndVideoTask implements Callable<String>{
        private final CountDownLatch latch;
        private final int i;


        public getAwayAudioAndVideoTask(CountDownLatch latch, int i) {
            this.latch = latch;
            this.i = i;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public String call() throws Exception {
            latch.countDown();
            return String.valueOf(CountryEnum.forEach_countryEnum(i).getCountry()) + "\t 国被灭!";
        }
    }
    /**
     * countDownLatch替换成CompletableFuture
     * @throws InterruptedException
     */
    private static void CompletableFutureDemo1() throws InterruptedException, ExecutionException {
        //定义一个线程池
        CustomThreadPool mypool = new CustomThreadPool();
        ThreadPoolExecutor threadPool = mypool.createPool("收集视频");
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<String> list = new ArrayList<>(6);
        for (int i = 1; i < 7; i++) {
            int finalI = i;
            CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //System.out.println(String.valueOf(CountryEnum.forEach_countryEnum(finalI).getCountry()) + "\t 国被灭!");
                return String.valueOf(CountryEnum.forEach_countryEnum(finalI).getCountry()) + "\t 国被灭!";
            }, threadPool);
            //result.join();
            list.add(result.get());
        }
        list.forEach(System.out::println);
        stopwatch.stop();
        String string = stopwatch.toString();
        System.out.println(string);
        System.out.println(Thread.currentThread().getName() + "\t 秦国,一统华夏!");
        mypool.shutdown();
    }
}
