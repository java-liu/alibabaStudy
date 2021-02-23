package com.sanshao.utils;

/**
 * @Description: 数组公共类
 * @Author: Liuys
 * @CreateDate: 2021/2/18 10:58
 * @Version: 1.0
 */
public class Commons {
    public static void print(int[] nums){
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num + " ");
        }
        System.out.println(sb.toString());
    }

    public static void print(char[] s){
        StringBuilder sb = new StringBuilder();
        for (char num : s) {
            sb.append(num + " ");
        }
        System.out.println(sb.toString());
    }
}
