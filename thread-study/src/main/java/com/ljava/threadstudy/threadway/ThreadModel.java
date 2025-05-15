package com.ljava.threadstudy.threadway;

public class ThreadModel extends Thread {
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("线程" + Thread.currentThread().getName() + "正在干活:" + i);
           /* try {
                Thread.sleep(100); // Sleep for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    public static void main(String[] args) {
        ThreadModel threadModel = new ThreadModel();
        threadModel.setName("Thread-1");
        threadModel.start();

        ThreadModel threadModel2 = new ThreadModel();
        threadModel2.setName("Thread-2");
        threadModel2.start();
    }

}
