package com.collections;

import java.util.*;

/**
 * @Description: set转List
 * @Author: Liuys
 * @CreateDate: 2020/4/9$ 14:06$
 * @Version: 1.0
 */
public class SetToList {
    public static void main(String[] args){
        Demo1();
    }
    public static void Demo1(){
        String[] staffs = new String[]{"java","mysql","js"};
        Set<String> staffsSet = new HashSet<>(Arrays.asList(staffs));

        List<String> result = new ArrayList<>(staffsSet);

        result.forEach(str->{
            System.out.println(str);
        });
    }
}
