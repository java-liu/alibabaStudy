package com.sanshao.demo;


import com.google.common.base.Stopwatch;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import javafx.scene.paint.Stop;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GetRandom {
    public static void main(String[] args) {
        //StopWatch spring guava java都有提供
        Stopwatch stopwatch = Stopwatch.createStarted();
        /**
         * 从1-100个数字中随机获取7个不重复的数字
         */
        Map<Integer, Integer> map = new HashMap<>(7);
        while (map.size() != 7){
            Integer key = (int)(Math.random() * 100) + 1;
            if(!map.containsKey(key)){
                map.put(key, key);
            }
        }
        System.out.println(map);
        SecureRandom secureRandom = new SecureRandom();
        int nextInt = secureRandom.nextInt(2000);
        System.out.println("任务1预算耗时:"+ nextInt);
        Multiset<Integer> multiset = HashMultiset.create(2);
        multiset.add(1);
        multiset.add(2);
        multiset.add(2);
        System.out.println(multiset.size());
        System.out.println(multiset.count(2));
        System.out.println(multiset.count(1));

        //实际用时
        System.out.println("用时" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "毫秒");
        System.out.println(stopwatch.isRunning());
        stopwatch.stop();
    }
}
