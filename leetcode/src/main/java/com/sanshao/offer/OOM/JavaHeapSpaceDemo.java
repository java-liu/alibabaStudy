package com.sanshao.offer.OOM;

import java.util.Random;

/**
 * @Description: Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * @Author: Liuys
 * @CreateDate: 2021/4/19 14:25
 * @Version: 1.0
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        //二、大对象
        //byte[] bytes = new byte[10 * 1024 * 1024];
        //一、产生大量对象
        String str = "java";
        while (true){
            str += str + new Random().nextInt(1111111) +  new Random().nextInt(33333333);
            str.intern();//放入堆String pool里面
        }
    }
}
