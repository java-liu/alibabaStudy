package com.example.demo.service.impl;

import com.example.demo.service.IUserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


/**
 * @description 说明：在实现类上添加@Profile注解，并在注解参数中指定前述配置文件中的｛profile｝值，用于指定该实现类所适用的环境。
 * @author Liuys
 * @version V1.0
 * @Package com.example.demo.service.impl
 * @date 2019/9/24 13:38
 */
@Service
//@Profile("prod")
public class UserProduceServiceImpl implements IUserService {
    @Override
    public String send() {
        System.out.println("UserProduceServiceImpl的send()");
        return "生产环境下使用的业务接口实现类";
    }
}
