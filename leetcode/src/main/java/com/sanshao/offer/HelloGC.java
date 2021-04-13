package com.sanshao.offer;

/**
 * 如何查看一个正在运行中的程序，它的某个jvm参数是否开启？具体值是多少？
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("********HelloGC");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
