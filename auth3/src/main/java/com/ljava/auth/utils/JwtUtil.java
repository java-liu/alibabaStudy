package com.ljava.auth.utils;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import io.jsonwebtoken.Jwts;

import java.security.*;
import java.util.Date;

/**
 * 公钥和私钥生成：生成一次并持久化存储。
 * 公钥和私钥加载：在应用启动时从文件中加载公钥和私钥。
 * JWT生成：使用私钥签名。
 * JWT验证：使用公钥验证。
 * 这样可以确保公钥和私钥在不同的请求和会话之间保持一致，提高系统的安全性和可靠性。
 */
public class JwtUtil {
    //private static final String JWT_SECRET_KEY = "ljava-ljava-ljava-ljava-ljava-secret-key"; //密钥
    private static final long EXPERATION_TIME =  10 * 60 * 1000; //10分钟过期

    //private static final Key key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes());

    private static final KeyPair keyPair;

    static {
        //每次应用启动时，都会生成一对新的公钥和私钥。这种做法在实际应用中通常是不可取的，
        // 因为公钥和私钥应该在生成后被持久化存储，以便在不同的请求和会话之间保持一致。
        /*try {
            keyPair = KeyGenerator.generateRsaKeyPair();
            System.out.println(
                    "公钥:" + keyPair.getPublic().toString() + "\n" +
                            "私钥:" + keyPair.getPrivate().toString()
            );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }*/
        try {
            keyPair = KeyGenerator.loadKeys();
            System.out.println(
                    "公钥:" + keyPair.getPublic().toString() + "\n" +
                            "私钥:" + keyPair.getPrivate().toString()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load keys", e);
        }
    }

    private static final PrivateKey privateKey = (PrivateKey) keyPair.getPrivate();

    private static final PublicKey publicKey = (PublicKey) keyPair.getPublic();


    /**
     * 生成JWT 使用私钥签名
     * @param username
     * @return
     */
    public static String generateToken(String username){
        /**
         * 创建JWT构建器：初始化JWT构建器对象。
         * 设置主题：将JWT的主题设置为传入的用户名。
         * 设置签发时间：记录JWT的签发时间。
         * 设置过期时间：计算并设置JWT的过期时间。
         * 签名：使用密钥和指定的签名算法对JWT进行签名。
         * (签名:使用密钥对JWT进行签名,新版本jjwt,可以利用密钥对象自动推断算法,不再需要指定算法)
         * 生成令牌：将所有设置压缩成一个字符串形式的JWT并返回
         */
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPERATION_TIME))
                .signWith(privateKey)
                .compact();
    }

    /**
     * 验证JWT 使用公钥验证
     * @param token
     * @return
     */
    public static String validateToken(String token){
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseUnsecuredClaims(token)
                .getPayload()
                .getSubject();
    }

    public static String entryData(String body){
        SM2 sm2 = new SM2(null, publicKey);
        return sm2.encryptHex(body.getBytes(), KeyType.PublicKey);
    }

    public static void main(String[] args) {
        System.out.println(entryData("ljava"));
    }

}
