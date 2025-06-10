package com.ljava.vocabapp.service;

import com.ljava.vocabapp.entity.UserEntity;

public interface UserService {

    /**
     * 根据用户名加载用户信息
     * @param username 用户名
     * @return 返回用户对象
     */
    UserEntity loadUserByUsername(String username);

    /**
     * 验证用户密码是否正确
     * @param rawPassword 明文密码
     * @param encodedPassword 加密后的密码
     * @return 如果匹配返回 true，否则返回 false
     */
    boolean checkPassword(String rawPassword, String encodedPassword);
}
