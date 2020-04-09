package com.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: 数组转set
 * @Author: Liuys
 * @CreateDate: 2020/4/9$ 10:17$
 * @Version: 1.0
 */
public class arrayToSet {
    public static void main(String[] args){
        Demo1();
        /**
         * list,map,set的区别
         * list,map,set的区别（首先假定小狗都是同一个细胞克隆出来的）
         * List = 排成一长队的小狗
         * Map = 放在一个个，有笼子号的屋子里面的一群
         * Set = 一群小狗挂上号，然后直到一个圈里
         * HashSet 它不保证集合的迭代顺序，特别是它不保证该顺序恒久不变。
         * LinkedHashSet定义了迭代顺序，即按照装饰元素插入到集合中的顺序（插入顺序）进行迭代。
         */
    }
    public static void Demo1(){
        String[] staffs = new String[]{"java","mysql","js"};
        Set<String> stringSet = new HashSet<>(Arrays.asList(staffs));
        stringSet.add("elastic");//OK
        stringSet.remove("js");//OK
        /***
         * 迭代遍历
         */
        Iterator<String> it = stringSet.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        /**
         * for循环遍历
         */
        for(String str : stringSet){
            System.out.println(str);
        }
        /***
         * 优点还体现在泛型，假如 set中存放的是Object
         */
        Set<Object> set = new HashSet<Object>();
        //for循环遍历
        for (Object obj : set){
            if (obj instanceof Integer){
                int aa = (Integer) obj;
            }else if(obj instanceof String){
                String string = (String) obj;
            }else{
                System.out.println("其它类型");
            }

        }
    }
}
