package com.sanshao.string;

/**
 * @Description: 测试String线程安全
 * @Author: Liuys
 * @CreateDate: 2021/4/20 11:02
 * @Version: 1.0
 */
public class StringDemo3 {


    public static void main(String[] args) {
        String s = new String("aaa");
        //s.intern();
        appendStr(s);
        System.out.println("String s>>>>" + s.toString());
        //StringBuilder做参数
        StringBuilder sb = new StringBuilder("aaa");
        appendSb(sb);
        System.out.println("String sb>>>>" + sb.toString());
    }

    /***
     * 不可变的String
     * @param s
     * @return
     */
    public static String appendStr(String s){
        s += "bbb";
        return s;
    }

    /***
     * 可变的StringBuilder
     * @param sb
     * @return
     */
    public static StringBuilder appendSb(StringBuilder sb){
        sb.append("bbb");
        return sb;
    }
}
