package com.maizi.calendar;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @Description: Calendar用法
 * @Author: Liuys
 * @CreateDate: 2020/5/13 16:07
 * @Version: 1.0
 */
public class Main {
    public static void Demo1(){
        Calendar c = Calendar.getInstance();
        //获取当前时间
        System.out.println(c.getTime());
        //转换为long打印
        System.out.println("    YEAR = " + c.get(Calendar.YEAR));
        // 注意月份从0开始
        System.out.println("    MONTH = " + c.get(Calendar.MONTH));
        System.out.println("    DAY = " + c.get(Calendar.DAY_OF_MONTH));
        //注意星期从1开始：星期日= 1，星期六=7
        System.out.println("    WEEKDAY = " + c.get(Calendar.DAY_OF_WEEK));
        System.out.println("    HOUR = " + c.get(Calendar.HOUR_OF_DAY));
        System.out.println("    Minute = " + c.get(Calendar.MINUTE));
        System.out.println("    Second = " + c.get(Calendar.SECOND));
        System.out.println("    Millis = " + c.get(Calendar.MILLISECOND));
        //默认时区
        System.out.println("    TimeZone = " + c.getTimeZone());
    }

    public static void Demo2(){
        Calendar c = Calendar.getInstance();
        System.out.println(c.getTime());
        //设置为指定时间
        c.clear();
        c.set(Calendar.YEAR, 1999);
        c.set(Calendar.MONTH, 10);
        //如果日，时，分，秒，都没有设置，全部变成默认值 0
        System.out.println(c.getTime());
    }

    public static void Demo3(){
        Calendar c = Calendar.getInstance();
        //获取纽约时间
        c.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int d = c.get(Calendar.DAY_OF_MONTH);
        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        System.out.println(y + "-" + m + "-" + d + " " + hh +":" + mm +":"+ ss);
    }
    public static void Demo4(){
        Calendar c = Calendar.getInstance();
        System.out.println(c.getTime());
        //+ 5 day
        c.add(Calendar.DAY_OF_MONTH,90);
        c.add(Calendar.HOUR_OF_DAY, -1);
        System.out.println(c.getTime());
    }
    public static void main(String[] args){
        Demo4();
    }
}
