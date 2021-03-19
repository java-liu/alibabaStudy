package com.sanshao.offer;


/***
 * 单例模式,这种在多线程情况下不行(单机版的)
 */
public class Singleton {
    private static Singleton instance = null;
    private Singleton(){
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
    }
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    public static void main(String[] args){
       /* System.out.println(Singleton.getInstance() == Singleton.getInstance());
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
        System.out.println(Singleton.getInstance() == Singleton.getInstance());*/


        /***
         * 并发多线程后，情况发生了很多变化
         */
        for(int i = 1;i <= 10; i++){
            new Thread(() -> {
                Singleton.getInstance();
            }, String.valueOf(i)).start();
        }
        /*1	 我是构造方法SingletonDemo()
        5	 我是构造方法SingletonDemo()
        4	 我是构造方法SingletonDemo()
        3	 我是构造方法SingletonDemo()
        2	 我是构造方法SingletonDemo()*/
    }
}
