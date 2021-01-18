package com.sanshao.service.impl;

import com.sanshao.service.学生守则接口;
import com.sanshao.service.零食小商贩;

/**
 * @Description: 接口实现类，其中接口中有些方法实现类不愿意实现。。。。
 * @Author: Liuys
 * @CreateDate: 2020/6/15 16:19
 * @Version: 1.0
 */
public class 小学生 extends 零食小商贩 {

    /***
     * 每周请代签的人看一次电影
     */
    @Override
    public void watchMovie() {
        System.out.println("看电影");
    }
}
