package com.sanshao.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: ArrarList线程不安全Demo
 * @Author: Liuys
 * @CreateDate: 2021/3/23 11:26
 * @Version: 1.0
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args){
        List<String> list = new CopyOnWriteArrayList<>();
        //list.stream().forEach(System.out::println);
        for(int i = 1; i <= 30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        /***
         * 1 故障现象
         *  java.util.ConcurrentModificationException
         * 2 导致原因
         *   并发争抢修改导致,
         *   一个线程正在写入,另一个线程过来争抢,导致数据不一致异常.并发修改异常.
         *
         * 3 解决方案
         *  3.1 new Vector<>()
         *  3.2 Collections.synchronizedList(new ArrayList<>());
         *  3.3 new CopyOnWriteArrayList();
         *
         * 4 优化建议(同样的错误不犯第二次)
         */
    }
}
