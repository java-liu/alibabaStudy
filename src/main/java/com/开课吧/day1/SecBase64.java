package com.开课吧.day1;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Description: Base64是编码算法，不是加密算法
 * Base64编码的目的是把任意二进制数据编码为文本（长度增加1/3）
 * 其它编码：Base32,Base48,Base58
 * @Author: Liuys
 * @CreateDate: 2020/5/20 15:07
 * @Version: 1.0
 */
public class SecBase64 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String original = "Hello\u00ff编码测试";
        //withoutPadding()方法去掉==号
        String b64 = Base64.getEncoder().withoutPadding().encodeToString(original.getBytes());
        String b642 = Base64.getUrlEncoder().withoutPadding().encodeToString(original.getBytes());

        System.out.println(b64);
        String ori = new String(Base64.getDecoder().decode(b64), "UTF-8");
        String ori2 = new String(Base64.getUrlDecoder().decode(b64), "UTF-8");

        System.out.println(ori);
    }
}
