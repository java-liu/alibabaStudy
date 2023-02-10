package com.example.redisstudy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisStudyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void demo1(){
        //设置 k,v
        stringRedisTemplate.opsForValue().set("name", "lys");
        //取值
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
    }

    @Test
    public void demo2() throws InterruptedException {
        //设置 k,v 以及过期时间
        stringRedisTemplate.opsForValue().set("code", "001", 2, TimeUnit.SECONDS);
        Thread.sleep(3000);
        System.out.println(stringRedisTemplate.opsForValue().get("code")); //null
    }

    @Test
    public void demo3(){
        stringRedisTemplate.opsForValue().set("key", "hello world");
        //从偏移量开始对给定的 key 的 value 进行覆写,若 key 不存在,则前面偏移量为空
        stringRedisTemplate.opsForValue().set("key", "redis", 6);
        System.out.println(stringRedisTemplate.opsForValue().get("key")); //hello redis
    }

    @Test
    public void demo4(){
        stringRedisTemplate.opsForValue().set("name", "test");
        //若key不存在，设值
        stringRedisTemplate.opsForValue().setIfAbsent("name", "test2");
        System.out.println(stringRedisTemplate.opsForValue().get("name"));//还是test
    }

    @Test
    public void demo5() {
        //批量k,v设值
        Map<String, String> map = new HashMap<String, String>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        stringRedisTemplate.opsForValue().multiSet(map);

        //批量取值
        List<String> keys = new ArrayList<String>();
        keys.add("k1");
        keys.add("k2");
        keys.add("k3");
        List<String> values = stringRedisTemplate.opsForValue().multiGet(keys);
        System.out.println(values);

        //批量设值，若key不存在，设值
        //redisTemplate.opsForValue().multiSetIfAbsent(map);

    }

    @Test
    public void demo6() {
        stringRedisTemplate.opsForValue().set("name", "demo6");
        //设值并返回旧值，无旧值返回null
        System.out.println(stringRedisTemplate.opsForValue().getAndSet("name", "demo7"));
        //如果之前有,返回新的值
        System.out.println(stringRedisTemplate.opsForValue().get("name")); //demo7
        //(头次执行)返回 null,再执行返回 demo7
        System.out.println(stringRedisTemplate.opsForValue().getAndSet("bbbb", "demo7"));
    }

    @Test
    public void demo7() {
        //自增操作，原子性。可以对long进行double自增，但不能对double进行long自增，会抛出异常
        stringRedisTemplate.opsForValue().increment("count", 1L);//增量为long
        System.out.println(stringRedisTemplate.opsForValue().get("count"));

        stringRedisTemplate.opsForValue().increment("count", 1.1);//增量为double
        System.out.println(stringRedisTemplate.opsForValue().get("count"));
    }

    @Test
    public void demo8() {
        //key不存在，设值
        stringRedisTemplate.opsForValue().append("str", "hello");
        System.out.println(stringRedisTemplate.opsForValue().get("str"));
        //key存在，追加
        stringRedisTemplate.opsForValue().append("str", " world");
        System.out.println(stringRedisTemplate.opsForValue().get("str"));

    }

    @Test
    public void demo9() {
        stringRedisTemplate.opsForValue().set("key", "hello world");
        //value的长度
        System.out.println(stringRedisTemplate.opsForValue().size("key"));//11
    }

    @Test
    public void demo10() {
        stringRedisTemplate.opsForValue().set("bitTest","a");
        // 'a' 的ASCII码是 97  转换为二进制是：01100001
        // 'b' 的ASCII码是 98  转换为二进制是：01100010
        // 'c' 的ASCII码是 99  转换为二进制是：01100011

        //因为二进制只有0和1，在setbit中true为1，false为0，因此我要变为'b'的话第六位设置为1，第七位设置为0
        stringRedisTemplate.opsForValue().setBit("bitTest",6, true);
        stringRedisTemplate.opsForValue().setBit("bitTest",7, false);
        System.out.println(stringRedisTemplate.opsForValue().get("bitTest"));

        //判断offset处是true1还是false0
        System.out.println(stringRedisTemplate.opsForValue().getBit("bitTest",7));
    }
}
