package com.ljava.somemethod.dateMethod;

import com.alibaba.excel.util.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateDifference {
    public static void main(String[] args) {
        method1();
    }
    public static void method1(){
        //时间格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT_19);
        //当前时间
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        String evalBeganTime = "2023-06-25 09:20:00";
        LocalDateTime evalTime = LocalDateTime.parse(evalBeganTime, formatter);
        //开始时间与当前时间比较,如果第一个值大于第二个值,结果为负  表示还没到开始时间
        //如果第一个值小于第二个值,结果为正,表示已经到了开始时间
        long secends = ChronoUnit.SECONDS.between(evalTime, localDateTimeNow);

        Double anew = Double.parseDouble("1.0");
        long b = new Double(anew * 60 * 60).longValue();

        if(secends > b){
            System.out.println("111");
        }
        System.out.println(secends);

    }
}
