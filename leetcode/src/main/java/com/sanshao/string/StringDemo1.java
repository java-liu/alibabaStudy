package com.sanshao.string;

/**
 * @Description: String特例
 * @Author: Liuys
 * @CreateDate: 2021/4/19 18:10
 * @Version: 1.0
 */
public class StringDemo1 {
    public static final String A = "ab";
    public static final String B  = "cd";
    public static void main(String[] args) {
        String s = A + B;
        String t = "abcd";
        if(s == t){
            System.out.println("s等于t，是同一个对象");
        }else{
            System.out.println("s不等于t，不是同一个对象");
        }
    }
}
