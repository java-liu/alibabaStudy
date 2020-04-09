package com.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 数组转list
 * @Author: Liuys
 * @CreateDate: 2020/4/9$ 9:48$
 * @Version: 1.0
 */
public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args){
        arrayToList();
    }

    /***
     * 需要注意的是,Arrays.asList()返回一个受指定数组决定的固定大小的列表。所以不能做add、remove等操作，否则会报错。
     */
    public static void arrayToList(){
        String[] staffs = new String[]{"java","mysql","js"};
        List staffsList = Arrays.asList(staffs);
        //Arrays.asList()返回一个受指定数组决定的固定大小的列表，所以不能做add、remove等操作，否则会报错。
        //下面两行会抛出UnsupportedOperationException（不支持操作异常）异常
        //UnsupportedOperationException异常是运行时异常，不需要显示声明与捕获
        ///staffsList.add("php");
        ///staffsList.remove(0);
        //如果想再做增删操作呢？将数组中的元素一个一个添加到列表，这样列表的长度就不固定了，可以进行增删操作。
        List newList = new ArrayList<String>();
        for(String temp : staffs){
            newList.add(temp);
        }
        newList.add("elastic");//OK
        newList.remove(0);//OK
        newList.forEach(str->{
            System.out.println(str);
        });
    }
}
