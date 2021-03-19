package com.sanshao.offer;

/***
 *
 */
public class SingletonDemo2 {
    private static SingletonDemo2 instance = null;
    private SingletonDemo2(){
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
    }

    /***
     * 这种锁粒度更小
     * DCL(Double Check Lock 双端检锁机制)
     * @return
     */
    public static SingletonDemo2 getInstance(){
        if(instance == null){
            synchronized (SingletonDemo2.class){
                if(instance == null){
                    instance = new SingletonDemo2();
                }
            }
        }
        return instance;
    }
    /***
     * 这种锁 太重(重锁，尽量不要用)
     * @return
     */
    public static synchronized SingletonDemo2 getInstance1(){
        if(instance == null){
            instance = new SingletonDemo2();
        }
        return instance;
    }




    public static void main(String[] args){
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo2.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
