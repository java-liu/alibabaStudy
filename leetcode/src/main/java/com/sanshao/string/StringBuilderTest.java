package com.sanshao.string;

import java.util.HashMap;
import java.util.Map;

/**
 * StringBuilder去除最后一个多余的字符
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "400");
        map.put("msg", "测试");
        map.put("data", "数据");
        StringBuilder sb = new StringBuilder();
        for(String key: map.keySet()){
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        //System.out.println(method1(sb));
        //System.out.println(method2(sb));
        System.out.println(method3(sb));

    }

    public static String method1(StringBuilder sb) {
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static String method2(StringBuilder sb) {
        return sb.substring(0, sb.length() - 1).toString();
    }

    public static String method3(StringBuilder sb) {
        System.out.println(sb.lastIndexOf("&"));
        return sb.deleteCharAt(sb.lastIndexOf("&")).toString();
    }
}
