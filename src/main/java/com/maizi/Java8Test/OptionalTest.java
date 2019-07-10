package com.maizi.Java8Test;

import com.maizi.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @ClassName OptionalTest
 * @Description
 * 1、Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象
 * 2、Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 * 3、Optional 类的引入很好的解决空指针异常
 * @Author liuys
 * @Date 2019/7/10 9:16
 * @Verson 1.0
 **/
public class OptionalTest {
    private final static Logger logger = LoggerFactory.getLogger(OptionalTest.class);
    public static void main(String[] args){
        OptionalTest optionalTest = new OptionalTest();

        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable --允许传递为null参数
        Optional<Integer> a = Optional.ofNullable(value1);

        Optional<Integer> b = Optional.of(value2);
        //logger.info(optionalTest.sum(a,b).toString());

        //------------字符串测试began-------------
        String str1 = "";
        String str2 = null;

        Optional<String> oStr = Optional.ofNullable(str1);
        Optional<String> oStr2 = Optional.ofNullable(str2);
        Optional<String> oStr3 = Optional.of(str1);
        // Optional.of -- 如果传递的参数是null，抛出异常NullPointException
        ///Optional<String> oStr4 = Optional.of(str2);
        logger.info(optionalTest.demo(oStr,oStr2));
        ///logger.info(optionalTest.demo(oStr3,oStr4));
        //------------字符串测试end-------------

        //----------------对象测试began-------
        Person p1 = null;
        Person p2= new Person("张三");
        //Optional.of -- 如果传递的参数是null，抛出异常NullPointException
        ///Optional<Person> oP1 = Optional.of(p1);
        Optional<Person> oP2 = Optional.of(p2);
        Optional<Person> oP3 = Optional.ofNullable(p2);
        Optional<Person> oP4 = Optional.ofNullable(p1);

        ///System.out.println(optionalTest.demo1(oP1,oP2));
        System.out.println(optionalTest.demo1(oP3,oP4));
        //----------------对象测试end-------



    }

    /***
     * 数字
     * @param a
     * @param b
     * @return
     */
    public Integer sum(Optional<Integer> a,Optional<Integer> b){
        //Optional.isPresent --判断值是否存在
        logger.info("第一个参数值存在:" + a.isPresent());
        logger.info("第二个参数值存在:" + b.isPresent());

        //Optional.orElse --如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        Integer value2 = b.get();

        return value1 + value2;
    }

    /***
     * 字符串 "" null测试
     * @param str1
     * @param str2
     * @return
     */
    public String demo(Optional<String> str1,Optional<String> str2){
        //Optional.isPresent --判断是否存在
        logger.info("第一个参数存在：" + str1.isPresent());
        logger.info("第二个参数存在：" + str2.isPresent());
        return null;
    }

    /***
     * 实体类
     * @param p1
     * @param p2
     * @return
     */
    public Person demo1(Optional<Person> p1,Optional<Person> p2){
        //Optional.isPresent --判断是否存在
        logger.info("第一个参数存在：" + p1.isPresent());
        logger.info("第一个参数存在：" + p2.isPresent());
        Person person = p1.orElse(new Person("aaa"));
        Person person2 = p2.orElse(new Person("aaa"));
        return person2;
    }
}
