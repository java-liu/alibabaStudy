package com.lintcode;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import org.apache.commons.lang3.StringUtils;

/**
 * 给定一个字符串和一个偏移量，根据偏移量旋转字符串(从左向右旋转)
 * 对于字符串 "abcdefg".
 * offset=0 => "abcdefg"
 * offset=1 => "gabcdef"
 * offset=2 => "fgabcde"
 * offset=3 => "efgabcd"
 * 在数组上原地旋转，使用O(1)的额外空间
 * @author Liuys
 * @version V1.0
 * @Package com.lintcode
 * @date 2019/8/13 9:38
 */
public class RotateString {
    public static void main(String[] args){
        rotateString01("abcdefg",3);
        RotateString rs = new RotateString();
        rs.rotateString02("abcdefg",3);
    }


    /***
     * 第一种方法：先将所有的字符向后移动一们，然后将最后一个字符放到第一个字符上。重复过程
     * @param str
     * @param offset
     */
    public static void rotateString01(String str,int offset){
        char temp;
        // 不旋转 返回原str
        if(offset == 0) {return;}
        if(str.length() == 0){ return;}

        //将字符串转化成字符数组
        char[] ch = str.toCharArray();
        int len = ch.length;
        offset = offset%len;
        for(int i = 1; i <= offset; i++){
            //把最后一个字符保存起来
            temp = ch[len - 1];
            int j = len - 2;

            while (j >= 0 ){
                ch[j + 1] = ch [j];
                j--;
            }
            ch[0] = temp;
        }
        str = new String(ch);
        System.out.println(str);
    }

    public void rotateString02(String str,int offset){
        // 不旋转 返回原str
        if(offset == 0) {return;}
        if(str.length() == 0){ return;}

        //将字符串转化成字符数组
        char[] ch = str.toCharArray();
        int len = ch.length;
        offset = offset%len;
        //先把字符串，整个旋转，abcdefg -> gfedcba
        reserve(ch, 0, len -1 );
        //再把 gfedcba 按offset
        reserve(ch, 0, offset - 1);
        //System.out.println(new String(ch));
        reserve(ch,offset, len - 1);
        System.out.println(new String(ch));
    }
    private void reserve(char[] ch, int start, int end){
        //char[] ch = str.toCharArray();
        //int len = ch.length;
        for(int i = start,j = end; i < j; i++,j--){
            //exchange(ch[i], ch[j]);
            exchange(ch, i, j);
        }
        //System.out.println(new String(ch));
    }
    private void exchange(char a,char b){
        char tmp = a;
        a = b;
        b = tmp;
    }
    private void exchange(char[] ch,int x, int y){
        char xx = ch[x];
        char yy = ch[y];
        ch[x] = yy;
        ch[y] = xx;

    }

    /***
     * 字符数组、字符串数组转换成字符串
     */
    @Test
    public void charsToString(){
        //第一种方法。直接声明
        char[] ch = {'a','b','c','d'};
        String s = new String(ch);
        //abcd
        System.out.println(s);

        //字符串数组to字符串
        //-------------------------------第一种、遍历-------------------------
        //String是不可变类---利用StringBuffer
        String[] strs = {"aa","bed","jid"};
        //线程安全
        StringBuffer sb = new StringBuffer();
        //线程不安全
        StringBuilder sb1 = new StringBuilder();
        for(int i = 0; i < strs.length; i++){
            sb1.append(strs[i]);
        }
        String str = sb1.toString();
        // aabedjid
        System.out.println(str);
        //-------------------------------第一种、遍历-------------------------
        //-------------------------------第二种、StringUtils的join方法-------------------------
        //数组转字符串org.apache.commons.lang3.StringUtils
        //数组转字符串，其实使用的也是遍历
        String str3 = StringUtils.join(strs);
        // aabedjid
        System.out.println(str3);
        //数组转字符串（逗号分隔）
        String str4 = StringUtils.join(strs,",");
        // aa,bed,jid
        System.out.println(str4);
        //-------------------------------第二种、StringUtils的join方法-------------------------
        //-------------------------------第三种、ArrayUtils的toString方法-------------------------
        //数组转字符串，（逗号分隔，首尾加大括号）
        String str2 = ArrayUtils.toString(strs,",");
        //{aa,bed,jid}
        System.out.println(str2);
        //-------------------------------第三种、ArrayUtils的toString方法-------------------------


    }
}
