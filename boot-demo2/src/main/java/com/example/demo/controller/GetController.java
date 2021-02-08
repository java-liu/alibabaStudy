package com.example.demo.controller;

import com.example.demo.entity.Depart;
import com.example.demo.entity.User;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description: SpringBoot 获取Get请求参数详解
 * @Author: Liuys
 * @CreateDate: 2021/1/28 20:30
 * @Version: 1.0
 */
@RestController
@RequestMapping("/getTest")
public class GetController {

    /***
     * 参数直接在路径中
     * @param name
     * @return
     */
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        return "获取到的name是：" + name;
    }

    /***
     * 参数跟在？号后面
     * @param name
     * @return
     */
    @GetMapping("/hello1")
    public String hello1(@RequestParam("name") String name){
        return "获取到的name是：" + name;
    }

    /***
     * 参数没有传递的情况
     * @param name
     * @return
     */
    @GetMapping("/hello2")
    public String hello2(@RequestParam(value = "name", required = false) String name){
        return "获取到的name是：" + name;
    }

    /***
     * 参数没有传递，使用默认值
     * @param name
     * @return
     */
    @GetMapping("/hello3")
    public String hello3(@RequestParam(value = "name", defaultValue = "xxx") String name){
        return "获取到的name是：" + name;
    }

    /***
     * 使用map来接收参数
     * @return
     */
    @GetMapping("/hello4")
    public String hello4(@RequestParam Map<String, Object> params){
        return "name:"+ params.get("name") + "\nage:" + params.get("age");
    }

    /***
     * 接收一个数组
     * @param names
     * @return
     */
    @GetMapping("/getArray")
    public String hello5(@RequestParam("name") String[] names){
        String result = "";
        for(String name:names){
            result += name + "\n";
        }
        return result;
    }

    /***
     * 使用对象来接收参数
     * @param user
     * @return
     */
    @GetMapping("/getPoJo")
    public String hello6(User user){
        return "name:"+ user.getName() + "\nage:" + user.getAge();
    }

    /***
     * 指定对象前缀接收参数
     * @param user
     * @return
     */
    @GetMapping("/userPoJoBinder")
    public String userPoJoBinder(@ModelAttribute("u") User user){
        return "name: "+ user.getName() + "\nage: "+ user.getAge();
    }
    @InitBinder("u")
    private void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("u.");
    }

    /***
     * 构造多个对象来接收参数
     * @param user
     * @param depart
     * @return
     */
    @GetMapping("/userPojo")
    public String hello7(User user, Depart depart){
        return "name: "+ user.getName() + "\nage: "+ user.getAge() + "\nphone:" + depart.getPhone();
    }

}
