package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName UserController
 * @Description
 * @Author Lenovo
 * @Date 2019/7/24 16:21
 * @Verson 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/ajaxDemo")
    public String index(Map<String,Object> result){
        result.put("name","Liuys");
        return "ajax_demo";
    }

    @RequestMapping("/getAjax")
    public String getAjax(){
        return "/ajax_user";
    }


    @RequestMapping("/getAjaxPost")
    public String getAjaxPost(){
        return "/ajax_user";
    }

    /***
     * ajax json demo
     * @return
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(){
        User user = new User();
        user.setId(1);
        user.setName("Liuys");
        return user;
    }

    @RequestMapping("/md5Test")
    public String md5Test(Map<String,Object> result){
        result.put("name","Liuys");
        return "Md5Test";
    }
}
