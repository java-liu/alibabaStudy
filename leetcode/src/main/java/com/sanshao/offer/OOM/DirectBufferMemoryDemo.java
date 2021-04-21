package com.sanshao.offer.OOM;

import java.nio.ByteBuffer;

/**
 * @Description: 直接内存
 * JVM配置参数：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 故障现象：
 * Exception in thread "main" java.lang.OutOfMemoryError:Direct buffer memory
 * 导致原因：
 * 写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）与缓冲区（Buffer）的I/O方式，
 * 它可以使用Native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作。
 * 这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据。
 *
 * ByteBuffer.allocate(capability)第一种方式是分配JVM堆内存，属于GC管辖范围，由于需要拷贝所以速度相对较慢
 *
 * ByteBuffer.allocateDirect(capability) 第二种方式是分配OS本地内存，不属于GC管辖范围，由于不需要内在拷贝所以速度相对较快。
 * @Author: Liuys
 * @CreateDate: 2021/4/20 16:11
 * @Version: 1.0
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        //本机最大的直接内存,默认为物理内存的四分之一
        System.out.println("配置的maxDirectMemory:" + (sun.misc.VM.maxDirectMemory()/(double)1024 / 1024) + "MB");
        //-XX:MaxDirectMemorySize=5m  我们配置为5M，但是实际使用6M，故意出问题
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
