package com.maiz.Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName TestList
 * @Description
 * @Author Lenovo
 * @Date 2019/5/29 14:20
 * @Verson 1.0
 **/
public class TestList {
    public static  void main(String[] args){
        //Demo1();
        Demo2();
    }
    /**
    * remove元素请使用Iterator方式，如果并发操作，需要对Iterator对象加锁
    * @author      yongshan.liu
    * @param
    * @return
    * @throws
    * @date        2019/5/29 16:12
    */
    public static void Demo1(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            if(it.next().equals("2")){
                it.remove();
            }
        }
        print(list);
    }
   /**
   * 最后一个元素remove时会报错，可以参考ArrayList源码中的907行
   * Exception in thread "main" java.util.ConcurrentModificationException
   * @author      yongshan.liu
   * @param
   * @return      
   * @throws      
   * @date        2019/5/29 16:07
   */
    public static void Demo2(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        for (String item:list){
            if("3".equals(item)){
                list.remove(item);
            }
        }
        print(list);
    }

    public static void print(List<String>  list){
        for (String str:list){
            System.out.println(str);
        }
    }
}
