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
        //Demo1();
        Demo2();
    }
    public static void Demo1(){
        String[] staffs = new String[]{"java","mysql","js"};
        Set<String> staffsSet = new HashSet<>(Arrays.asList(staffs));

        List<String> result = new ArrayList<>(staffsSet);

        result.forEach(str->{
            System.out.println(str);
        });
    }

    public static void Demo2(){
        String parentCode = "3.1";
        String stepCode = "3.1.6";
        String next = "";
        String nextCode = stepCode.substring(stepCode.indexOf(".",stepCode.indexOf(".")+1) + 1, stepCode.length());
        StringBuilder sb = new StringBuilder();
        sb.append(parentCode);
        sb.append(".");
        sb.append(String.valueOf(Integer.parseInt(nextCode) + 1));
        String nextChild = parentCode + "." + String.valueOf((Integer.parseInt(nextCode) + 1));
        next =  parentCode + "." + "1";
        System.out.println(sb);
        System.out.println(nextChild);
        System.out.println(next);
    }
}
