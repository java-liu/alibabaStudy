package com.sanshao.offer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable{

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     Thread#run()
     */
    @Override
    public void run() {

    }
}
class MyThread2 implements Callable<Integer>{

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}



/**
 * @Description: Callable类
 * @Author: Liuys
 * @CreateDate: 2021/4/2 16:07
 * @Version: 1.0
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread1(),1);
        new Thread(futureTask).start();
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());
        new Thread(futureTask1).start();
        int result = futureTask.get();
        int result2 = futureTask1.get();
        System.out.println(result);
        System.out.println(result2);
    }

}
