package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SampleController
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
}
