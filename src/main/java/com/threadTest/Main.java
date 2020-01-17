package com.threadTest;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.threadTest
 * @date 2020/1/15 10:15
 */
public class Main {
    public static void main(String[] args){
        new Thread("Thread#1"){
            @Override
            public void run(){
                update();
            }
        }.start();
        new Thread("Thread#2"){
            @Override
            public void run(){
                update();
            }
        }.start();
        new Thread("Thread#3"){
            @Override
            public void run(){
                update();
            }
        }.start();
    }

    private static ThreadLocal<Integer> initValue = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 10;
        }
    };

    public static void update(){
        initValue.set(initValue.get() + 66);
        System.out.println(initValue.get());
    }
}
