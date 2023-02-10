package com.example.redisstudy.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtils {

    //两种实现方式, RedisTemplate StringRedisTemplate
    private static RedisTemplate<String, Object> redisTemplate;
    private static StringRedisTemplate stringRedisTemplate;

    static {
        redisTemplate = (RedisTemplate<String, Object>) ApplicationContextUtil.getContext().getBean("redisTemplate");
        stringRedisTemplate = (StringRedisTemplate) ApplicationContextUtil.getContext().getBean("stringRedisTemplate");
    }


    public static Object get(String cacheName, String key) {
        Object object = redisTemplate.opsForValue().get(key);
        return object;
    }

    public static String getStr(String key){
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }

    /**
     * 设置对象
     * @param key
     * @param value
     */
    public static void setObject(String key, Object value){
        setObject(key, value, null);
    }

    /**
     * 设置对象,带过期时间
     * @param key
     * @param value
     * @param timeOut
     */
    public static void setObject(String key, Object value, Long timeOut){
        redisTemplate.opsForValue().set(key, value);
        if(timeOut != null){
            redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取对象
     * @param key
     * @return
     */
    public static Object getObject(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
