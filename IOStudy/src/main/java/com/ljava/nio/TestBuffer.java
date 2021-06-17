package com.ljava.nio;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * @Description:
 * 一、缓冲区（Buffer）：在Java NIO中负责数据的存取。缓冲区就是数组，用于存储不同数据类型的数据
 * 根据数据类型的不同（boolean除外），提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * DoubleBuffer
 * FloadBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 *
 * 二、缓冲区取数据的两个核心方法：
 * put():存入数据到缓冲区
 * get():获取缓冲区的数据
 *
 * 三、缓冲区中的四个核心属性
 * capacity：容量，表示缓冲区最大存储数据的容量，一旦声明不能改变
 * limit：界限，表示缓冲区中可以操作数据的大小。（limit后不能进行读写）
 * position:位置，表示缓冲区中正在操作数据的位置
 *
 * position<=limit<=capacity
 *
 * @Author: Liuys
 * @CreateDate: 2021/6/17 15:46
 * @Version: 1.0
 */
public class TestBuffer {

    @Test
    public void test1(){

        String str = "abcde";
        //1、分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("--------allocate()--------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        //2、利用put()存入数据到缓冲区中
        byteBuffer.put(str.getBytes());

        System.out.println("------------put()----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());

        //3、切换读取数据模式
        byteBuffer.flip();

        System.out.println("------------put()----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());

        //4、利用get()
    }
}
