package com.sanshao.control;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2020/6/16 13:47
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args){
        SubClass1 sub = new SubClass1();
        //调用父类的方法。父类已经定义了方法的执行顺序
        //sub.executeMethod();
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        //sb.append("aaaa");
        map.put("errorMsg",sb);
        map.put("errorFlag",true);
        System.out.println(sb1.append((StringBuilder)map.get("errorMsg")));
        System.out.println((Boolean) map.get("errorFlag"));

        System.out.println(true || false);
    }
}
