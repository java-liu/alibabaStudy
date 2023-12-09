package com.sanshao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/***
 * 多线程优化
 * 在循环里跑多线程，提高执行速度
 */
public class ThreadMultis {

    private Integer theadCount; //线程数量

    private Integer size; //集合大小

    private Integer timeOut = 60; //超时时间（单位：分钟）

    private Function function; //执行方法


    public interface Function {
        void run(int i);
    }

    public ThreadMultis(int theadCount, int size, Function function) {
        this.theadCount = theadCount;
        this.size = size;
        this.function = function;
    }

    public ThreadMultis(int theadCount, int timeOut, int size, Function function) {
        this.theadCount = theadCount;
        this.timeOut = timeOut;
        this.size = size;
        this.function = function;
    }

    public void start() throws InterruptedException, TheadMultisException {
        int size = this.size; //集合总数
        int theadCount = this.theadCount; // 执行线程数量
        int splitCount = size / theadCount + (size % theadCount != 0 ? 1 : 0); //计算分拆数量，向上取整
        final CountDownLatch cdl = new CountDownLatch(size); //计数器

        for (int i = 1; i <= theadCount; i++) {
            final int beign = (i - 1) * splitCount;
            final int end = (i * splitCount) > size ? size : i * splitCount;
            if (beign >= end) break;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = beign; j < end; j++) {
                        try {
                            function.run(j);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // 闭锁-1
                        cdl.countDown();
                    }
                }
            }).start();
        }

        int time = this.timeOut != null ? this.timeOut : 60;
        // 调用闭锁的await()方法，该线程会被挂起，它会等待直到count值为0才继续执行
        // 这样我们就能确保上面多线程都执行完了才走后续代码
        try {
            if (!cdl.await(time, TimeUnit.MINUTES)) {
                throw new TheadMultisException("Executed for more than " + time + " minutes");
            }
        } catch (InterruptedException e) {
            throw e;
        }
    }

    public class TheadMultisException extends Exception {

        public TheadMultisException() {
            super();
        }

        public TheadMultisException(String s) {
            super(s);
        }
    }

    public static void main(String[] args) throws Exception {
        // 初始化数据
        final List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> object = new HashMap<>();
            object.put("index", i);
            list.add(object);
        }

        new ThreadMultis(2, 1, list.size(), new ThreadMultis.Function() {
            @Override
            public void run(int i) {
                Thread t = Thread.currentThread();
                String name = t.getName();
                // 模拟运行耗时
                try {
//                    Thread.sleep(1000 * 60);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Map<String, Object> object = list.get(i);
                System.out.println(name + "：执行到" + object.get("index"));
                object.put("status", "已经执行过");
            }
        }).start();

        System.out.println("====== 线程结束 =====");
        // 校验多线程正确性
        for (Map<String, Object> object : list) {
            System.out.println(object.get("index") + ":" + object.get("status"));
        }
    }
}
