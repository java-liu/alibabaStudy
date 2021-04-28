package com.sanshao.offer.GC;

/**
 * @Description:
 * 1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC   (DefNew+Tenured)
 *
 * 2
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC   (ParNew+Tenured)
 * 备注情况：
 * Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 *
 * 3
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelGC -XX:+PrintCommandLineFlags  (PSYoungGen+ParOldGen)
 * 4
 * 4.1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC (PSYoungGen+ParOldGen)
 * 4.2 不加就是默认UseParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags                       (PSYoungGen+ParOldGen)
 * 5
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC (Par new generation + concurrent generation)
 * 6
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 * 7 (实际上在java8已经被优化掉了，没有了)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 * @Author: Liuys
 * @CreateDate: 2021/4/21 11:27
 * @Version: 1.0
 */
public class GCDemo {
    public static void main(String[] args) {

    }
}
