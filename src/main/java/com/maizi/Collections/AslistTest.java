package com.maizi.Collections;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName AslistTest
 * @Description
 * @Author Lenovo
 * @Date 2019/5/29 16:54
 * @Verson 1.0
 **/
public class AslistTest {

    /**
    * 使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方 法，
     * 它的 add/remove/clear 方法会抛出 UnsupportedOperationException 异常。
     * 说明：asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。
     * Arrays.asList 体现的是适配器模式，只是转换接口，后台的数据仍是数组。
    * @author      yongshan.liu
    * @param
    * @return
    * @throws
    * @date        2019/5/29 16:58
    */
    public static void Demo1(){
        String[] str = new String[]{"you","wu"};
        List list = Arrays.asList(str);
        //下面这句话会报错，UnsupportedOperationException
        //list.add("bao");
        //改变str中的元素值，list中的元素也会随着改变
        str[0] = "gu";
        print(list);
    }

    public static void print(List<String>  list){
        for (String str:list){
            System.out.println(str);
        }
    }



    public static void main(String[] args){
        Demo1();
    }
}
