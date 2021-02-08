package com.example.demo.current;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/2/8 21:04
 * @Version: 1.0
 */
public class OrderLock {
    /***
     * 加锁状态，记录加锁的次数
     */
    private volatile int state = 0;


    /***
     * 当前线程持有者
     */
    private Thread lockHolder;

    /***
     * 加锁
     */
    public void lock(){
        //CAS比较与交换 ---原子算法

    }

    public void unLock(){

    }
}
