package com.sanshao.offer;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 公平锁和非公平锁
 * 可重入锁(也叫递归锁)
 * 指的是同一线程外层函数获得锁之后,内层递归函数仍然能获取该锁的代码,
 * 在同一个线程在外层方法获取锁的时候,在进入内层方法会自动获取锁.
 *
 * 也就是说,线程可以进入任何一个它已经拥有的锁所同步着的代码块.
 * @Author: Liuys
 * @CreateDate: 2021/3/29 15:11
 * @Version: 1.0
 */
public class ReentLockDemo {
    public static void main(String[] args){
        //synchronizedReentLock();
        //reentrantLockReentLock();
        String s = "https%3A%2F%2Fhspublic-resource.oss-cn-beijing.aliyuncs.com%2Fbjdlzyjgpub-dev%2F69cb490c4aca416a92a8c03ed5112ea3%3FExpires%3D1617095201%26OSSAccessKeyId%3DjoephPauQ1t6AAKU%26Signature%3DvQBgYQUuSe4XoOV08QJZV1vwS%252BQ%253D";
        try {
            String encode = URLDecoder.decode(s, "UTF-8");
            System.out.println(encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /***
     * 典型的可重入锁方式一:synchronized
     */
    private static void synchronizedReentLock() {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
    }

    /***
     * 典型的可重入锁方式二:ReentrantLock
     */
    private static void reentrantLockReentLock(){
        Phone phone = new Phone();
        Thread t1 = new Thread(phone);
        Thread t2 = new Thread(phone);

        t1.start();
        t2.start();

    }
}

class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
        sendEmail();
    }
    public  void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId() + "\t =======invoked sendEmail()");
    }

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
        get();
    }
    ReentrantLock lock = new ReentrantLock();
    public void get(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getId() + "\t =======invoked get()");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        //lock.lock();
        try{
            System.out.println(Thread.currentThread().getId() + "\t =======invoked set()");
        }finally {
            //lock.unlock();
        }
    }
}
