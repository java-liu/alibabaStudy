package com.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: set转数组
 * @Author: Liuys
 * @CreateDate: 2020/4/9$ 13:59$
 * @Version: 1.0
 */
public class SetToArray {
    public static void main(String[] args){
        Demo1();
    }
    public static void Demo1(){
        String[] staffs = new String[]{"java","mysql","js"};
        Set<String> staffsSet = new HashSet<>(Arrays.asList(staffs));

        //set转数组，转完后为Object类型
        Object[] result = staffsSet.toArray();
        for(Object obj : result){
            System.out.println(obj);
        }
    }
}
