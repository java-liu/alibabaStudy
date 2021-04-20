package com.sanshao.offer.reference;

import java.lang.ref.SoftReference;

/***
 * 软引用
 * @author lys
 * @date 2021.04.17
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        softRef_Memory_Enough();
    }

    /***
     * 内存够用的时候就保留，不够用就回收
     */
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());


        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /***
     * 内存够用的时候就保留，不够用就回收
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        //System.gc();
        try{
            byte[] bytes = new byte[11*1024*1024];
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }
}
