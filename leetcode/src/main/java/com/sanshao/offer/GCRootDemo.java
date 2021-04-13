package com.sanshao.offer;

/***
 * @described
 * 在java中，可作为GC Roots的对象有：
 * 1、虚拟机栈（栈桢中的本地变量表）中引用的对象
 * 2、方法区中类静态属性引用的对象
 * 3、方法区中常量引用的对象
 * 4、本地方法栈中（JNI）即一般说的Native方法中引用的对象
 * @date 2021.04.13
 * @author lys
 *
 */
public class GCRootDemo {
    private  byte[] bytes = new byte[1024*1024*1024];

    //private static GCRootDemo2 t2;
    //private static final GCRootDemo3 t3 = new GCRootDemo3(8);
    public static void main(String[] args) {
        m1();
    }
    public static void m1(){
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }
}
