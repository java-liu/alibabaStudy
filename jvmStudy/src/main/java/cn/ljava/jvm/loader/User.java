package cn.ljava.jvm.loader;

import lombok.Data;

@Data
public class User {
    private String name;
    private Integer id;

    public void working(){
        System.out.println("user working");
    }
}
