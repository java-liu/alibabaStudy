package com.sanshao.offer;

import java.util.concurrent.*;

public class ThreadDemo {
    public static void main(String[] args) throws Exception {
        // 1.创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 2.定义CompletionService
        CompletionService<String> service = new ExecutorCompletionService<>(executorService);
        // 3.添加任务
        for (int i = 0; i < 10; i++) {
            //普通写法
            /*service.submit(new Callable<String>() {
                public String call() {
                    return "线程：" + Thread.currentThread().getName() + "执行完成";
                }
            });*/
            // lambda写法
            //service.submit(() -> "线程：" + Thread.currentThread().getName() + "执行完成");
            Future<String> submit = service.submit(new CallBackDemo());
            String s = submit.get();
            System.out.println(s + "=========");
        }
        // 4.获取结果
        for (int i = 0; i <= 10; i++) {
            System.out.println(service.take().get());
        }
        // 关闭线程池
        executorService.shutdown();
    }


}
