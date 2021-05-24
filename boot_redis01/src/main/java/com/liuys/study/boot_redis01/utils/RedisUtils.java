package com.liuys.study.boot_redis01.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/5/21 14:09
 * @Version: 1.0
 */
public class RedisUtils {
    private static JedisPool jedisPool;

    static{
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        GenericObjectPoolConfig poolConfig;
        jedisPool = new JedisPool(jedisPoolConfig, "172.18.8.116",6379 ,-1, "password");
    }

    public static Jedis getJedis() throws Exception{
        if(null != jedisPool){
            return jedisPool.getResource();
        }
        throw new Exception("Jedispool is not ok");
    }
}
