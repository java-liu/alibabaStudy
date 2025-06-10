package com.ljava.vocabapp.provider;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * JwtTokenProvider 负责生成、解析和验证 JWT token。
 */
@Component
public class JwtTokenProvider {

    /**
     * 密钥用于签名JWT token，应保持安全并避免泄露。
     */
    @Value("${jwt.secret}")
    private String secretKey; //从配置文件注入

    /**
     * token的有效期（以毫秒为单位）
     */
    @Value("${jwt.expiration}")
    private long validityInMilliseconds;


    public String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    /**
     * 生成一个新的JWT token。
     *
     * @param username 用户名，通常用于设置主题(subject)
     * @return 返回生成的token字符串
     */
    public String generateToken(String username) {
        MacAlgorithm hs256 = Jwts.SIG.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        /*if(validityInMilliseconds == null){
            validityInMilliseconds = JWT_TTL;
        }*/
        long expMillis = nowMillis + validityInMilliseconds;
        return Jwts.builder()
                .id(getUuid()) //设置jti(JWT ID)：是JWT的唯一标识
                .subject(username) //设置sub(Subject)：代表这个JWT的主体，即它的所有人
                .signWith(generalKey(),hs256) //设置签名使用的签名算法和签名使用的秘钥
                .issuedAt(now) //设置iat(Issued At)：是指该JWT的签发时间
                .expiration(new Date(expMillis))//设置过期时间
                .compact();
    }

    /**
     * 解析给定的token并返回其中的主题(subject)，通常是用户名。
     *
     * @param token 需要解析的token
     * @return 返回token中的主体信息(例如用户名)
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(generalKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * 验证token是否有效。
     *
     * @param token 需要验证的token
     * @return 如果token是有效的则返回true，否则返回false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(generalKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 捕获可能的异常，如签名错误、过期等
            return false;
        }
    }


    /**
     * 生成加密后的密钥
     */
    public SecretKey generalKey() {
        byte[] encodedKey = secretKey.getBytes();
        //return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}

