package com.sanshao.string;

import java.util.Arrays;

/***
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn96us/
 */
public class IsAnagram {
    public static void main(String[] args) {
        String s = "你好嘛ddd";
        String t = "嘛好你ddd";
        System.out.println(isAnagram(s,t));

        /*String s1 = "？";
        char c111 = '？';
        System.out.println(c111);*/
    }
    public static boolean isAnagram(String s, String t){
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        if(sc.length != tc.length){
            return false;
        }
        Arrays.sort(sc);
        Arrays.sort(tc);
        for (int i = 0; i < sc.length; i++) {
            if(sc[i] != tc[i]){
                return false;
            }
        }
        return true;
    }
}
