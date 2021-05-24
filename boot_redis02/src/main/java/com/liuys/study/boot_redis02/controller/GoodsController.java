package com.liuys.study.boot_redis02.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/5/19 13:40
 * @Version: 1.0
 */
@RestController
public class GoodsController {

    public static final String REDIS_LOCK = "liuysLock";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private Redisson redisson;

    @GetMapping("/buyGoods")
    public String buyGoods(){
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        RLock lock = redisson.getLock(REDIS_LOCK);
        lock.lock();
        try {
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            if(goodsNumber > 0){
                int reealNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001",String.valueOf(reealNumber));
                System.out.println("成功买到商品，库存还剩下：" + reealNumber + "件" + "\t 服务提供端口" + serverPort);
                return "成功买到商品，库存还剩下：" + reealNumber + "件" + "\t 服务提供端口" + serverPort;
            }else{
                System.out.println("商品已经售完" + "\t"+ "服务提供端口：" + serverPort);
            }
            return "商品已经售完" + "\t"+ "服务提供端口：" + serverPort;
        } finally {
            /*if(lock.isLocked()){
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }*/
            if(lock.isLocked() && lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
}
