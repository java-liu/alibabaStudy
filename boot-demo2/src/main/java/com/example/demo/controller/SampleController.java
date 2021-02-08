package com.example.demo.controller;

import com.example.demo.entity.Depart;
import com.example.demo.entity.User;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @ClassName SpringBoot获取POST请求参数详解
 * @Description
 * @Author Lenovo
 * @Date 2019/7/24 14:04
 * @Verson 1.0
 **/
@RestController
@RequestMapping("/demo")
public class SampleController {
    @RequestMapping("/hello")
    public String index(){
        return "Hello boot";
    }

    @RequestMapping("/some")
    @ResponseBody
    public String someHandle(){
        int i = 5 / 0;
        return "Hello Spring Boot World";
    }

    /***
     * 接收form-data格式的POST数据
     * @param name
     * @param age
     * @return
     */
    @PostMapping("/user")
    public String hello(@RequestParam(value = "name", defaultValue = "xxx") String name,
                        @RequestParam(value = "age",required = false)Integer age){
        return "name:"+ name + "\nage:" + age;
    }

    /***
     * 使用map来接收参数
     * @param map
     * @return
     */
    @PostMapping("/userMap")
    public String userMap(@RequestParam Map<String, Object> map){
        return "name:"+ map.get("name") + "\nage:" + map.get("age");
    }
    /***
     * 使用数组来接收参数
     * @param names
     * @return
     */
    @PostMapping("/userArray")
    public String userArray(@RequestParam("name") String[] names){
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
    @PostMapping("/userPoJo")
    public String userPoJo(User user, Depart depart){
        return "name: "+ user.getName() + "\nage: "+ user.getAge() + "\nphone:" + depart.getPhone();
    }

    /***
     * 指定对象前缀接收参数
     * @param user
     * @return
     */
    @PostMapping("/userPoJoBinder")
    public String userPoJoBinder(@ModelAttribute("u") User user){
        return "name: "+ user.getName() + "\nage: "+ user.getAge();
    }
    @InitBinder("u")
    private void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("u.");
    }

    /***
     * 通过HttpServletRequest获取输入流读取文本内容
     * @param request
     * @return
     */
    @PostMapping("/inputRequest")
    public String inputRequest(HttpServletRequest request){
        ServletInputStream is = null;
        try{
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            int len = 0;
            while((len  = is.read(buf))!= -1){
                sb.append(new String(buf ,0, len));
            }
            System.out.println(sb.toString());
            return "获取到的文本内容为：" + sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(is != null){
                    is.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /***
     * 使用map来接收JSON数据
     * @param params
     * @return
     */
    @PostMapping("/mapForJson")
    public String mapForJson(@RequestBody Map params){
        return "name:"+ params.get("name") + "\nage:" + params.get("age");
    }
    /***
     * 使用bean来接收JSON数据
     * @param user
     * @return
     */
    @PostMapping("/beanForJson")
    public String beanForJson(@RequestBody User user){
        return "name:"+ user.getName() + "\nage:" + user.getAge();
    }
    /***
     * 使用bean来接收JSON数据
     * @param list
     * @return
     */
    @PostMapping("/beanListForJson")
    public String beanListForJson(@RequestBody List<User>list){
        String result = "";
        for(User user:list){
            result += user.getName() + " " +user.getAge() + "\n";
        }
        return result;
    }





}
