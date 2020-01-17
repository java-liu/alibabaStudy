package com.example.demo;

import com.example.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    //若接口只有一个实现类，则可以使用byType方式自动注入
    //但面在存在两个实现类，只能使用byName方式自动注入
    @Autowired
    @Qualifier("userServiceImpl")
    private IUserService userService;

    @Autowired
    @Qualifier("userProduceServiceImpl")
    private IUserService userProduceService;

    @Test
    public void contextLoads() {
        userService.send();
        userProduceService.send();
    }

}
