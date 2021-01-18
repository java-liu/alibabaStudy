package com.开课吧.day1;


import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: Hmac
 * Hash-based Message Authentication Code
 * 基于密钥的消息认证算法
 * 更安全的消息摘要算法
 * HmacMD5 约等于 md5(secure_random_key, data)
 * @Author: Liuys
 * @CreateDate: 2020/6/1 17:18
 * @Version: 1.0
 */
public class Hmac {

    public static void main(String[] args) throws Exception{
        //算法名称
        String algorithm = "HmacSHA1";
        //原始数据
        String data = "helloworld";
        //随机生成一个Key:
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        SecretKey skey = keyGen.generateKey();
        //打印Key:
        byte[] key = skey.getEncoded();
        System.out.println(String.format("Key :%0" + (key.length * 2) + "x", new BigInteger(1,key)));
        //用这个Key计算
        byte[] result = hmac(algorithm, skey, data.getBytes("UTF-8"));
        System.out.println(String.format("Hash : %0" + (result.length * 2) + "x", new BigInteger(1,result)));
    }

    /**
    * 方法实现说明
    * @author      Liuys
    * @param hmacAlgorithm Hmac算法
    * @param input 原始数据的输入
    * @return
    * @exception   
    * @date        2020/6/1 17:26
    */
    public static byte[] hmac(String hmacAlgorithm, SecretKey skey,byte[] input) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(hmacAlgorithm);
        mac.init(skey);
        mac.update(input);
        //返回Hmac
        return mac.doFinal();
    }


}
