package com.sanshao.string;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: LRU算法
 * @Author: Liuys
 * @CreateDate: 2021/5/24 10:07
 * @Version: 1.0
 */
public class LRUCacheDemo<K,V> extends LinkedHashMap<K,V> {
    private int capacity;//缓存坑位
    public LRUCacheDemo(int capacity){
        super(capacity, 0.75f, false);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //return super.removeEldestEntry(eldest);
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
        lruCacheDemo.put(1,"a");
        lruCacheDemo.put(2,"b");
        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(4,"d");
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3,"c");
        lruCacheDemo.put(3,"c");
        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(5,"x");
        System.out.println(lruCacheDemo.keySet());
    }
}
