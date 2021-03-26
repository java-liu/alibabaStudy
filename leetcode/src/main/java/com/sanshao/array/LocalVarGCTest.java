package com.sanshao.array;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/3/8 14:21
 * @Version: 1.0
 */
public class LocalVarGCTest {
    public static void main(String[] args){
        localVarGC1();
    }
    public static void localVarGC1(){
        long startTime = System.currentTimeMillis();
        byte[] a = new byte[10 * 1024 * 1024];
        System.gc();
        System.out.println("take time:" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
