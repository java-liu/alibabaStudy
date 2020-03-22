package com.easyexcel;

import lombok.Data;

import java.util.Date;

@Data
public class DemoData {
    private Integer id;
    private String code;
    private String name;
    private Integer count;
    private Date createTime;

    @Override
    public String toString() {
        return "DemoData:{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", createTime=" + createTime +
                '}';
    }
}
