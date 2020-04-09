package com.collections;

import java.util.*;

/**
 * @Description: list转set
 * @Author: Liuys
 * @CreateDate: 2020/4/9$ 11:23$
 * @Version: 1.0
 */
public class ListToSet {
    public static void main(String[] args){
        Demo1();
    }
    public static void Demo1(){
       String[] staffs = new String[]{"java","mysql","js"};
       List staffsList = Arrays.asList(staffs);

       //list转set,返回类型是Object
       Set result = new HashSet<>(staffsList);
       for(Object str : result){
           System.out.println(str);
       }
    }
}
