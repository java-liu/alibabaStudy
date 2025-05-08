package com.ljava.auth2.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.security.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    //token过期时间
    public static final Long JWT_TTL = 60 * 60 * 1000L; //millisecond

    //密钥明文
    public static final String JWT_KEY = "ljava";

    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成加密后的密钥
     */
    public static SecretKey generalKey(){
        byte[] encodedKey = JWT_KEY.getBytes();
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid){
        MacAlgorithm hs256 = Jwts.SIG.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis == null){
            ttlMillis = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        return Jwts.builder()
                .id(uuid) //设置jti(JWT ID)：是JWT的唯一标识
                .subject(subject) //设置sub(Subject)：代表这个JWT的主体，即它的所有人
                .signWith(generalKey(),hs256) //设置签名使用的签名算法和签名使用的秘钥
                .expiration(new Date(expMillis)); //设置过期时间
    }
    /**
     * 创建jwt
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis){
        String uuid = getUuid();
        return getJwtBuilder(subject, ttlMillis, uuid).compact();
    }

    /**
     * 解析
     * @param jwt
     * @return
     */
    public static String parseJWT(String jwt){
        return Jwts.parser()
                .verifyWith(generalKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }
}
