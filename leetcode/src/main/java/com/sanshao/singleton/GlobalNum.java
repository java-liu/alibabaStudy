package com.sanshao.singleton;

/**
 * @Description: 单例 饿汉模式
 * @Author: Liuys
 * @CreateDate: 2021/5/18 17:15
 * @Version: 1.0
 */
public class GlobalNum {
    private static GlobalNum gn = new GlobalNum();
    private int num = 0;
    private GlobalNum(){}
    public static GlobalNum getGn(){
        return gn;
    }
    public synchronized int getNum(){
        return ++num;
    }
}
