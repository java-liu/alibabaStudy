package com.example.demo.current;

import com.example.demo.util.UnsafeInstance;
import sun.misc.Unsafe;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.LockSupport;

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
     * 基于CAS算法的队列  保证入队和出队的安全
     */
    private ConcurrentLinkedDeque<Thread> waiters = new ConcurrentLinkedDeque<>();
    //linkedblockedQueue 是基于aqs实现的同步

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }

    public boolean aquire(){
        //CAS比较与交换 ---原子算法
        Thread currentThread = Thread.currentThread();
        //初始状态
        int c = getState();
        if(c == 0){
            //同步器还没有被持有
            //waiters.size() == 0表示第一次加锁
            //currentThread == waiters.peek()是T2-Tn加锁时的判断
            if((waiters.size() == 0 || currentThread == waiters.peek()) && compareAndSwapState(0,1)){
                setLockHolder(currentThread);
                return true;
            }
        }
        return false;
    }

    /***
     * 加锁
     */
    public void lock() {
        //加锁成功
        if(aquire()){
            return;
        }
        //CAS比较与交换 ---原子算法
        Thread currentThread = Thread.currentThread();
        //如果加锁不成功，放入队列中
        waiters.add(currentThread);
        //如果加锁不成功
        for(;;){
            //让出CPU使用权
            //Thread.yield();  -> 这种方式不合适
           /* try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*///-> 这种方式也不合适
            if((currentThread == waiters.peek()) && aquire()){
                //T2如果加锁成功，就把T2从队列中移除
                //把T2从队列中移除
                waiters.poll();
                break;
            }
            //阻塞当前线程,park是释放cpu使用权，
            LockSupport.park(currentThread);
        }

    }

    /***
     * 释放锁
     */
    public void unLock(){
        if(Thread.currentThread() != lockHolder){
            throw new RuntimeException("lockholder is not current thread!");
        }
        int state = getState();
        //释放锁的同时要唤醒队列中的其他线程
        if(compareAndSwapState(state,0)){
            setLockHolder(null);
            Thread first = waiters.peek();
            if(first != null){
                //Thread.notify()是随机唤醒
                LockSupport.unpark(first);
            }
        }
    }

    /***
     * 原子操作
     * @param except 预期值
     * @param update
     * @return
     */
    public final boolean compareAndSwapState(int except, int update){
        return unsafe.compareAndSwapInt(this, stateOffSet, except, update);
    }

    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    //偏移量
    private static final long stateOffSet;
    static{
        try{
            //找到state在对象OrderLock中的偏移量
           stateOffSet = unsafe.objectFieldOffset(OrderLock.class.getDeclaredField("state"));
        }catch (Exception e){
            throw new Error();
        }
    }
}
