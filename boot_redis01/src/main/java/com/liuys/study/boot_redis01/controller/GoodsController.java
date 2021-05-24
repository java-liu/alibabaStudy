package com.liuys.study.boot_redis01.controller;

import com.liuys.study.boot_redis01.utils.RedisUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Collections;
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
    public String buyGoods() throws Exception {
        String value = UUID.randomUUID().toString() +  Thread.currentThread().getName();
        try {
            //加锁
            //Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value);//SETNX
            //stringRedisTemplate.expire(REDIS_LOCK,10L, TimeUnit.SECONDS);
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value,10L,TimeUnit.SECONDS);//SETNX
            if(!flag){
                return "抢锁失败";
            }
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            if(goodsNumber > 0){
                int reealNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001",String.valueOf(reealNumber));
                System.out.println("成功买到商品，库存还剩下：" + reealNumber + "件" + "\t 服务提供端口" + serverPort);
                //解锁
                return "成功买到商品，库存还剩下：" + reealNumber + "件" + "\t 服务提供端口" + serverPort;
            }else{
                System.out.println("商品已经售完" + "\t"+ "服务提供端口：" + serverPort);
            }
            return "商品已经售完" + "\t"+ "服务提供端口：" + serverPort;
        } finally {
            /*//判断加锁和解锁是不是同一个客户端
            if(stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)){
                //若在此时,这把锁突然不是这个客户端的,则会误解锁
                stringRedisTemplate.delete(REDIS_LOCK);
            }*/

            /*while(true){
                stringRedisTemplate.watch(REDIS_LOCK);
                if(stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)){
                    stringRedisTemplate.setEnableTransactionSupport(true);
                    stringRedisTemplate.multi();
                    stringRedisTemplate.delete(REDIS_LOCK);
                    List<Object> list = stringRedisTemplate.exec();
                    if(list == null){
                        continue;
                    }
                }
                stringRedisTemplate.unwatch();
                break;
            }*/
            Jedis jedis = RedisUtils.getJedis();

            String script = "if redis.call('get',KEYS[1]) == ARGV[1]\n" +
                    "then\n" +
                    "    return redis.call('del',KEYS[1])\n" +
                    "else\n" +
                    "    return 0\n" +
                    "end";
            try{
                Object o = jedis.eval(script, Collections.singletonList(REDIS_LOCK), Collections.singletonList(value));
                if("1".equals(o.toString())){
                    System.out.println("------del redis lock ok");
                }else{
                    System.out.println("------del redis lock error");
                }
            }finally {
                if(null != jedis){
                    jedis.close();
                }
            }

        }
    }
}
