package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName UserService
 * @Description
 * @Author Liuys
 * @Date 2019/7/24 15:22
 * @Verson 1.0
 **/
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public PageInfo<User> findUserList(int page,int size){
        //开启分页插件，放在查询语句上面
        PageHelper.startPage(page,size);
        List<User> listUser = userMapper.findUserList();
        //封装分页之后的数据
        PageInfo<User> pageInfoUser = new PageInfo<>(listUser);
        return pageInfoUser;
    }
}
