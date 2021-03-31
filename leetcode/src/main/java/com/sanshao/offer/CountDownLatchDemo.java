package com.sanshao.offer;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: java类 CountDownLatch
 * @Author: Liuys
 * @CreateDate: 2021/3/31 16:18
 * @Version: 1.0
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        closeDoor();
    }

    /**
     * 前面所有都执行完,最后执行main线程
     * @throws InterruptedException
     */
    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i = 1; i <= 6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 国被灭!");
                countDownLatch.countDown();
            },String.valueOf(CountryEnum.forEach_countryEnum(i).getCountry())).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦国,一统华夏!");
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getCode());
        System.out.println(CountryEnum.ONE.getCountry());
        System.out.println(CountryEnum.ONE.getAge());
    }
}
