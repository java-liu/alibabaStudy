package com.sanshao.offer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/3/26 17:58
 * @Version: 1.0
 */
public class HashMapDemo3 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
