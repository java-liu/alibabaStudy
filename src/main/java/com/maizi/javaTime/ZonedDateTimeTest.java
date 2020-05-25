package com.maizi.javaTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Description: java.time
 * ZonedDateTime = LocalDateTime + ZoneId
 * ZonedDateTime:带时区的日期和时间
 * ZoneId：新的时区对象（取代旧的java.util.TimeZone）
 * Instant:时刻对象（epoch seconds）
 * @Author: Liuys
 * @CreateDate: 2020/5/14 16:53
 * @Version: 1.0
 */
public class ZonedDateTimeTest {
    public static void Demo1(){
        //当前时区的日期和时间
        ZonedDateTime zbj = ZonedDateTime.now();
        System.out.println(zbj);
        //纽约时区的当前日期和时间
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(zny);
    }

    public static void Demo2(){
        LocalDateTime ldt = LocalDateTime.now();
        //关联到当前默认时区
        ZonedDateTime bj = ldt.atZone(ZoneId.systemDefault());
        System.out.println(bj);
        ZonedDateTime ny = ldt.atZone(ZoneId.of("America/New_York"));
        System.out.println(ny);

        //转换到纽约时区
        ZonedDateTime ny2 = bj.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(ny2);
    }

    /***
     * Instant 时刻
     */
    public static void Demo3(){
        Instant.now().atZone(ZoneId.systemDefault());
        Instant ins = Instant.now();
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        System.out.println("ins = " + ins);
        System.out.println("now = " + now);
        Instant ins2 = ZonedDateTime.now().toInstant();
        System.out.println("ins2 = " + ins2);

        ZonedDateTime zdt = ins.atZone(ZoneId.of("Z"));
        System.out.println("zdt = " + zdt);

        // 单位是秒
        long epoch = ins.getEpochSecond();
        System.out.println(epoch);
    }
    public static void main(String[] args){
        Demo3();
    }
}
