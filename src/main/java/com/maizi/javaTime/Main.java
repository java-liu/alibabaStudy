package com.maizi.javaTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Description: java.time包 JDK》1.8
 * LocalDate
 * LocalTime
 * LocalDateTime
 * @Author: Liuys
 * @CreateDate: 2020/5/14 10:05
 * @Version: 1.0
 */
public class Main {
    public static void Demo1(){
        //当前日期
        LocalDate d = LocalDate.now();
        //当前时间
        LocalTime t = LocalTime.now();
        //当前日期和时间
        LocalDateTime dt = LocalDateTime.now();
        //严格按照ISO 8601格式打印
        System.out.println(dt);
        //指定日期和时间
        LocalDate d2 = LocalDate.of(2020, 8, 14);
        LocalTime t2 = LocalTime.of(15, 16, 17);

        LocalDateTime dt2 = LocalDateTime.of(2020, 8,11,15,16,17);
        LocalDateTime dt3 = LocalDateTime.of(d2,t2);
        System.out.println(dt2);
        System.out.println(dt3);
    }
    public static void Demo2(){
        LocalDate d = LocalDate.now();
        //本周第几天
        System.out.println("Week = " +d.getDayOfWeek().getValue());
        //英文 周几
        System.out.println("Week = " +d.getDayOfWeek());
    }

    public static void main(String[] args){
        Demo2();
    }
}
