package com.maizi.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Description: Epoch Time
 * @Author: Liuys
 * @CreateDate: 2020/5/13 14:53
 * @Version: 1.0
 */
public class Main {
    public static void Demo1(){
        //获取系统当前时间戳
        System.out.println(System.currentTimeMillis());
        Date now = new Date();
        System.out.println(now);
        //将Date转化为long
        long t = now.getTime();
        System.out.println(t);
        //把long转化成Date
        Date date = new Date(t);
        System.out.println(date);
    }
    public static void Demo2(){
        Date now = new Date();
        System.out.println(now.toString());
        //以GMT+00:00时区打印日期时间，已经被DateFormat.format(Date date)替换
        System.out.println(now.toGMTString());
        //以当前时区+当前Local打印日期时间; 已经被DateFormat.format(Date date)替换
        System.out.println(now.toLocaleString());
    }
    public static void Demo3(){
        Date now = new Date();
        //指定格式打印
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        System.out.println(sdf.format(now));
        System.out.println(sdf1.format(now));
        System.out.println(sdf2.format(now));
    }

    public static void Demo4() throws ParseException {
        //按系统Local解析日期时间：
        String s1 = "2020-05-13 15:34:21";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s1);
        System.out.println(date);
        //解析MMM时默认按照系统Local
        String s2 = "May/13/2020 15:34:21";
        Date date1 = new SimpleDateFormat("MMM/dd/yyyy HH:mm:ss", Locale.US).parse(s2);
        System.out.println(date1);
        //按ISO8601标准格式解析
        String iso = "2020-05-13T15:34:21";
        Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(iso);
        System.out.println(date2);
    }

    public static void main(String[] args) throws ParseException{
        //Demo2();
        Demo4();
    }
}
