package com.sanshao.offer.reference;

import java.lang.ref.WeakReference;

/***
 * 弱引用
 * @author lys
 * @date 2021.04.17
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("============");
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
