package com.sanshao.offer.OOM;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: JVM参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * GC回收时间过长时间会抛出OutOfMemoryError，过长的定义是：超过98%的时间用来做GC并且回收了不到2%的堆内在，连续多次GC都只回收了不到2%的极端情况下才会抛出。
 * 假如不抛出GC overhead limit exceeded错误会发生什么呢？那就是GC清理的这点内在很快会被再次填满，迫使GC再次执行，这样就形成恶性循环，
 * CPU使用率一直是100%，而GC却没有任何成果。
 * @Author: Liuys
 * @CreateDate: 2021/4/20 15:37
 * @Version: 1.0
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
       int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while(true){
                list.add((++i + "").intern());
            }
        } catch (Throwable e) {
            System.out.println("***************i" + i);
            //e.printStackTrace();
            throw e;
        }
    }
}
