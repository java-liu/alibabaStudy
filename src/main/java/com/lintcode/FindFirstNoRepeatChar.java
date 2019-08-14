package com.lintcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第一个只出现一次的字符（Java)
 * @author Liuys
 * @version V1.0
 * @Package com.lintcode
 * @date 2019/8/12 14:58
 */
public class FindFirstNoRepeatChar {
    private static final Logger logger = LoggerFactory.getLogger(FindFirstNoRepeatChar.class);
    public static void main(String[] args){
        //findFirstNoRepeat("aabbcddeffccdd");
        //System.out.println(findFirstNoRepeatByMap("aabbcddeffccdd"));
        System.out.println(FirstNotRepeatChar("aabbcddeffccdd"));

    }

    /***
     * 查找字符中只第一次只出现一次的字符（借助数组）
     * @param str
     * @return
     */
    public static char findFirstNoRepeat(String str){
        if(str == null || str.length() == 0){
            return '0';
        }
        int[] counts = new int[26];
        str = str.toLowerCase();
        int len = str.length();
        for(int i = 0; i < len; i++){
            //logger.info(String.valueOf(str.charAt(i)));
            counts [str.charAt(i) - 'a']++;
        }
        for (int count:counts){
            //System.out.println(count);
        }
        for(int i = 0; i < len; i++){
            if(counts[str.charAt(i) - 'a'] == 1){
                return str.charAt(i);
            }
        }
        return '0';
    }

    /***
     * 查找字符中只第一次只出现一次的字符（借助Map）
     * @param str
     * @return
     */
    public static char findFirstNoRepeatByMap(String str){
        if(str == null || str.length() ==0 ){
            return '0';
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        //防止出现大小写混乱
        str = str.toLowerCase();
        int len = str.length();
        int count = 0;
        for(int i = 0; i < len; i++){
            if(map.containsKey(str.charAt(i))){
                count = map.get(str.charAt(i));
                map.put(str.charAt(i),++count);
            }else{
                map.put(str.charAt(i),1);
            }
        }
        for(int i = 0; i< len; i++){
            if(map.get(str.charAt(i)) == 1){
                return str.charAt(i);
            }
        }
        return '0';
    }

    /***
     * 查找字符中只第一次只出现一次的字符（借助两个List）
     * @param str
     * @return
     */
    public static char FirstNotRepeatChar(String str){
        if(str == null || str.length() == 0){
            return '0';
        }
        //存储只出现一次的字符
        List<Character> list = new ArrayList<>();
        //存储已经确定重复的字符
        List<Character> repeat = new ArrayList<>();
        for(int i = 0; i< str.length(); i++){
            char c = str.charAt(i);
            if(!repeat.contains(c) && !list.contains(c)){
                list.add(Character.valueOf(c));
            }else{
                //说明该元素已重复
                list.remove(Character.valueOf(c));
                repeat.add(Character.valueOf(c));
            }
        }
        if(list.size() <= 0){
            return '0';
        }
        //返回指定字符串在此字符串中第一次出现处的索引
        System.out.println(str.indexOf(list.get(0)));
        return list.get(0);
    }
}
