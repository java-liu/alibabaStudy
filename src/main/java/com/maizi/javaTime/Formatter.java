package com.maizi.javaTime;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description: 新的DateTimeFormatter(不可变)
 * Formatter
 * 默认按照ISO标准进行格式化和解析：
 * yyyy-MM-dd
 * HH:mm:ss
 * HH:mm:ss:SSS
 * yyyy-MM-dd'T'HH:mm:ss
 * yyyy-mm-dd'T'HH:mm:ss.SSS
 * @Author: Liuys
 * @CreateDate: 2020/5/14 10:18
 * @Version: 1.0
 */
public class Formatter {
    public static void Demo1(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = dtf.format(LocalDateTime.now());
        System.out.println(date);

        LocalDateTime dt2 = LocalDateTime.parse("2020-11-30 15:16:17",dtf);
        System.out.println(dt2);

        LocalDateTime dt3 = LocalDateTime.parse("2020-11-30T15:16:17");
        System.out.println(dt3);
    }

    /***
     * 日期和时间运算
     */
    public static void Demo2(){
        LocalDate today = LocalDate.now();
        LocalDate after5days = today.plusDays(5);
        System.out.println(after5days);

        //-2时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before2hours = now.minusHours(2);
        System.out.println(before2hours);

        // +1月-2周
        LocalDate d = today.plusMonths(3).minusWeeks(2);
        System.out.println(d);
    }

    //对日期和时间进行调整
    public static void Demo3(){
        //本月第1天
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
        //把秒和纳秒调整为0：
        LocalTime at = LocalTime.now().withSecond(0).withNano(0);

        System.out.println("本月第1天:" + firstDay);
        System.out.println("把秒和纳秒调整为0:" + at);

        //本月最后1天
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);
        //本月第1个周日
        LocalDate firstSunday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
        System.out.println(firstSunday);

    }

    /***
     * 时期和时间运算
     */
    public static void Demo4(){
        LocalDate d1 = LocalDate.of(2020, 11, 20);
        LocalDate d2 = LocalDate.of(2050, 1, 1);
        Period p = d1.until(d2);
        System.out.println(p);
    }
    public static void main(String[] args){
        Demo4();
    }
}
