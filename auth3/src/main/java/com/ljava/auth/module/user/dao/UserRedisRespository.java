package com.ljava.auth.module.user.dao;

import com.ljava.auth.module.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRedisRespository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //user info key prefix
    private static final String USER_KEY_PREFIX = "user:";

    //保存用户信息
    public void saveUser(String username, User user){
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        hashOps.put(USER_KEY_PREFIX + username, "username", user.getUsername());
        hashOps.put(USER_KEY_PREFIX + username, "password", user.getPassword());
        hashOps.put(USER_KEY_PREFIX + username, "email", user.getEmail());
    }

    //get user
    public User getUser(String username){
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        User user = new User();
        user.setUsername((String) hashOps.get(USER_KEY_PREFIX + username, "username"));
        user.setPassword((String) hashOps.get(USER_KEY_PREFIX + username, "password"));
        user.setEmail((String) hashOps.get(USER_KEY_PREFIX + username, "email"));
        return user;
    }

    //delete
    public void deleteUser(String username){
        redisTemplate.delete(USER_KEY_PREFIX + username);
    }
}
