package com.sanshao.offer.reference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/***
 * weakHashMap
 * @author lys
 * @date 2021.04.17
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("==========");
        myWeakHashMap();

       /* Integer i1 = new Integer(1);
        Integer i2 = 1;
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i2 == i1);
        System.out.println("---------------");
        int i = 10;
        long il = 10l;
        double id = 10d;
        System.out.println(i == il);
        System.out.println(i == id);*/
       int i = 222222222;
       Integer i2 = new Integer(222222222);
       Integer i3 = new Integer(222222222);
        System.out.println(i == i2);
        System.out.println(i == i3);
        System.out.println(i2 == i3);

    }

    private static void myHashMap(){
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        map.put(3,"WeakHashMap");
        System.out.println(map);
        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }
    private static void myWeakHashMap(){
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        Integer key1  = 128;
        String value = "HashMap";

        map.put(key, value);
        map.put(key1,"WeakHashMap");
        System.out.println(map);
        key = null;
        key1 = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            //System.out.println(iterator.next());
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
