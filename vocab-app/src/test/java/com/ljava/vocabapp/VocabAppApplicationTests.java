package com.ljava.vocabapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class VocabAppApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testPasswordMatch() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "111111";
        String encodedPassword = "$2a$10$GZ7Upy8vRwWzYh45K3UjCeTmDf9kFtHcJiBnXqOeLQgVx5A0P5N6Z";

        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("密码是否匹配: " + matches); // 应该输出 true 或 false

    }

    /**
     * 测试密码加密
     */
    @Test
    public void testPasswordEncode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("原始密码: " + rawPassword);
        System.out.println("加密后的密码: " + encodedPassword);
    }
}
