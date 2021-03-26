package com.sanshao.offer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/3/25 17:06
 * @Version: 1.0
 */
public class ContainerNotSafeDemo2 {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        Set<String> set1 = new HashSet<>();
        //list.stream().forEach(System.out::println);
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
