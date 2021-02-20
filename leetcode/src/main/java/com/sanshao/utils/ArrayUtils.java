package com.sanshao.utils;

import java.util.Set;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/2/18 15:14
 * @Version: 1.0
 */
public class ArrayUtils {
    /***
     * 将set集合转为int数组
     * @param allSet
     * @return
     */
    public static int[] SetToInt(Set<Integer> allSet){
        //先将set集合转为Integer数组
        //关键语句
        Integer[] temp = allSet.toArray(new Integer[]{});

        int[] intArray = new int[temp.length];
        for(int i = 0; i < temp.length;i++){
            intArray[i] = temp[i].intValue();
        }
        return intArray;
    }

    public static int[] SetToInt2(Set<Integer> allSet){
        //先将set集合转为Object对象数组（向上转型）
        Object[] objArray = allSet.toArray();
        int[] temp = new int[objArray.length];
        for(int i = 0; i < objArray.length; i++){
            temp[i] = (int)objArray[i];
        }
        return temp;
    }
}
