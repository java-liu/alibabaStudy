package com.example.demo.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description: 通过反射使用Unsafe魔法类
 * @Author: Liuys
 * @CreateDate: 2021/2/18 15:41
 * @Version: 1.0
 */
public class UnsafeInstance {
    public static Unsafe reflectGetUnsafe(){
        try{
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe)field.get(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
