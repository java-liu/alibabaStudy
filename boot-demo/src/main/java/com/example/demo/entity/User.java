package com.example.demo.entity;

import lombok.Data;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName User
 * @Description
 * @Author Liuys
 * @Date 2019/7/24 15:19
 * @Verson 1.0
 **/

public class User {
    private Integer id;
    private String name;
    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
