package com.sanshao.offer;

/**
 * 如何查看一个正在运行中的程序，它的某个jvm参数是否开启？具体值是多少？
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        //返回Java虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        //返回Java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("Total_MEMORY(-Xms)=" + totalMemory + "(字节)，" + (totalMemory/(double)1024/1024) + "MB");
        System.out.println("Total_MEMORY(-Xmx)=" + maxMemory + "(字节)，" + (maxMemory/(double)1024/1024) + "MB");
        //System.out.println("******HelloGC");
        //byte[] bytes = new byte[50 * 1024 * 1024];
        //Thread.sleep(Integer.MAX_VALUE);
    }
}
