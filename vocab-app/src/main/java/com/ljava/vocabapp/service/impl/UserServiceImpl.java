package com.ljava.vocabapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ljava.vocabapp.entity.UserEntity;
import com.ljava.vocabapp.mapper.UserMapper;
import com.ljava.vocabapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名加载用户信息
     *
     * @param username 用户名
     * @return 返回用户对象
     */
    @Override
    public UserEntity loadUserByUsername(String username) {
        log.info("Loading user: {}", username);
        UserEntity user = new UserEntity();
        try {
            user =  userMapper.selectOne(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUsername, username));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error loading user: {}", e.getMessage());
        }
        return user;
        //return userMapper.findByUsername(username);
    }

    /**
     * 验证用户密码是否正确
     *
     * @param rawPassword     明文密码
     * @param encodedPassword 加密后的密码
     * @return 如果匹配返回 true，否则返回 false
     */
    @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return false;
    }
}
