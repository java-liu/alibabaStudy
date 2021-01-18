package com.开课吧.day1;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @Description: JAVA摘要算法测试
 * JDK支持的标准算法名称：
 * 可以访问 http://docs.oracle.com/javase/6/docs/technotes/guides/security/StandardNames.html#MessageDigest
 * 第三方提供的算法 ；BouncyCastle 提供了 RipeMD160算法 http://www.bouncycastle.com
 * @Author: Liuys
 * @CreateDate: 2020/6/1 10:25
 * @Version: 1.0
 */
public class SHA {

    public static void main(String[] args) throws Exception {
        String str = "SHA1摘要算法测试";
        byte[] r = sha1(str.getBytes("UTF-8"));
        System.out.println(String.format("%040x", new BigInteger(1, r)));
        String str2 = "JAVA摘要算法测试";
        byte[] input = str2.getBytes("UTF-8");
        //把BouncyCastle作为Provider添加到java.security
        Security.addProvider(new BouncyCastleProvider());


        // 标准算法名称：MD5 SHA-1 SHA-256
        // 可以访问 http://docs.oracle.com/javase/6/docs/technotes/guides/security/StandardNames.html#MessageDigest
        byte[] r1 = digest("MD5", input);
        //字节数
        System.out.println(r1.length + ":" + String.format("%0" + (r1.length) + "x", new BigInteger(1, r1)));
        byte[] r2 = digest("SHA-1",input);
        System.out.println(r2.length + ":" + String.format("%0" + r2.length + "x", new BigInteger(1, r2)));
        byte[] r4 = digest("SHA-256",input);
        System.out.println(r4.length + ":" + String.format("%0" + r4.length + "x", new BigInteger(1, r4)));
        //这个是第三方支持的算法，需要把BouncyCastle作为Provider添加到java.security，否则会报错
        byte[] r3 = digest("RipeMD160",input);
        System.out.println(r3.length + ":" + String.format("%0" + r3.length + "x", new BigInteger(1, r3)));


    }

    public static byte[] sha1(byte[] input){
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-1");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        md.update(input);
        return md.digest();
    }

    /***
     * java摘要算法测试
     * @param hashAlgorithm 摘要算法名称
     * @param input
     * @return
     */
    public static byte[] digest(String hashAlgorithm, byte[] input){
        MessageDigest md;
        try{
            md = MessageDigest.getInstance(hashAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(input);
        return md.digest();
    }
}
