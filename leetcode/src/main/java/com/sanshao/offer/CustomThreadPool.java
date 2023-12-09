package com.sanshao.offer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadPool{
    private ThreadPoolExecutor threadPoolExecutor;
    private CountDownLatch countDownLatch;
    @Getter
    private String threadName;
    private int threshold;

    public CustomThreadPool(){}
    public CustomThreadPool(int threshold, String threadName) {
        this.threshold = threshold;
        this.threadName = threadName;
        this.init();
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
    public ThreadPoolExecutor createPool(String threadName) {
        this.threadName = threadName;
        this.init();
        return threadPoolExecutor;
    }

    private void init() {
        //线程等待时间
        int keepAliveTime = 600000;
        //获取java虚拟机数量
        int core = Runtime.getRuntime().availableProcessors() * 2;

        final AtomicInteger threadNumber = new AtomicInteger(1);
        threadPoolExecutor = new ThreadPoolExecutor(core, core, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), r -> {
            SecurityManager s = System.getSecurityManager();
            String namePrefix = "pool-" + threadName + "-thread-";
            return new Thread((s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup(), r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
        });
        countDownLatch = new CountDownLatch(threshold);
        System.out.println(threadName + "线程池初始化完成");
        //log.info(threadName + "线程池初始化完成");
    }

    /**
     * 初始化线程
     *
     * @return
     */
    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    /**
     * 关闭线程池
     */
    public void shutdown() throws InterruptedException {
        countDownLatch.await();
        //log.info(threadName, "{}执行结束，关闭线程池");
        System.out.println(threadName + "执行结束，关闭线程池");
        threadPoolExecutor.shutdown();
    }
}