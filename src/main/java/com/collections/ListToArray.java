package com.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: list转数组
 * @Author: Liuys
 * @CreateDate: 2020/4/9$ 11:07$
 * @Version: 1.0
 */
public class ListToArray {
    public static void main(String[] args){
        Demo1();
    }
    public static void Demo1(){
        List<String> staffsList = new ArrayList();
        staffsList.add("java");
        staffsList.add("mysql");
        staffsList.add("js");
        staffsList.add("elastic");
        //toArray()方法返回的是 Object类型数据
        //String[] strings = staffsList.toArray();
        Object[] result = staffsList.toArray();
        for (int i = 0; i < result.length; i++) {
            System.out.println((String) result[i]);
        }
    }
}
