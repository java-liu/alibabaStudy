package com.sanshao.offer.reference;

/***
 * 强引用
 * @author lys
 * @date 2021.04.17
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
        System.out.println(obj1);

    }
}
