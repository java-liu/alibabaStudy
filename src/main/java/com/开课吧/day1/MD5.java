package com.开课吧.day1;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2020/5/20 15:27
 * @Version: 1.0
 */
public class MD5 {
    public static byte[] toMD5(byte[] input){
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        md.update(input);
        return md.digest();
    }

    public static void main(String[] args) throws Exception{
        String s = "MD5摘要算法测试";
        byte[] r = toMD5(s.getBytes("UTF-8"));
        // 16进制表示的字节数组
        System.out.println(String.format("%032x",new BigInteger(1, r)));
    }
}
