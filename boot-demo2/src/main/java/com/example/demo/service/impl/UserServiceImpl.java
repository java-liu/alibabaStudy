package com.example.demo.service.impl;
import	java.net.Proxy;

import com.example.demo.service.IUserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.example.demo.service.impl
 * @date 2019/9/24 11:30
 */
@Service
//@Profile("dev")
public class UserServiceImpl implements IUserService {
    @Override
    public String send() {
        System.out.println("UserServiceImpl的send()");
        return "开发阶段使用的业务接口实现类";
    }
}
