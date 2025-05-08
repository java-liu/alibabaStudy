package com.ljava.auth2.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCache {

    @Resource
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象, Integer、String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setObject(final String key, final T value){
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象, Integer、String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param expireTime 过期时间
     * @param timeUnit 时间单位 时间颗粒度
     */
    public <T> void setObject(final String key, final T value, Long expireTime, final TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key, value, expireTime,timeUnit);
    }

    public boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, timeUnit));
    }
}
