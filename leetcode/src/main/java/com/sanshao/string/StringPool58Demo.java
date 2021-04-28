package com.sanshao.string;

/**
 * @Description: 58面试题
 * @Author: Liuys
 * @CreateDate: 2021/4/27 11:01
 * @Version: 1.0
 */
public class StringPool58Demo {
    public static void main(String[] args) {
        String str1 = new StringBuilder("58").append("tongcheng").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println("=================");
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());
    }
}
