package com.sanshao.string;

import com.sanshao.utils.Commons;

/**
 * @Description: 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: Liuys
 * @CreateDate: 2021/2/23 9:37
 * @Version: 1.0
 */
public class ReverseString {


    public static void reverseString(char[] s){
        int n = s.length, left = 0, right = s.length - 1;
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args){
        //Commons.print();
        char[] s = {'h','e','l','l','o'};

        char ch1, ch2;
        ch1 = 'Y';
        //char 字符数据类型的范围为0到65535
        ch2 = 88;
        System.out.println("ch1=" + ch1 + ",ch2= " + ch2);
        char ch3 = '\u0041';
        char ch4 = '\377';
        char ch5 = '/';
        //char c3 = '\a';
        char c1 = '\n';
        char c2 = '\\';
        System.out.println("ch3=" + ch3 + ",ch4= " + ch4);
        //reverseString(s);
        //Commons.print(s);


        char cha1 = 'X';
        System.out.println("cha1=" + cha1);
        cha1++;
        System.out.println("cha1 is now " + cha1);
    }
}
