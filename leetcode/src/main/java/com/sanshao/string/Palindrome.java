package com.sanshao.string;

/***
 * 2021.08.25
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 */
public class Palindrome {
    public static void main(String[] args) {
        String s = "ab,ccba";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s){
        if(s.length() == 0){
            return false;
        }
        //双指针，如果不是字母或者数字就跳过
        //方法Character.isLetterOrDigit(char c)判断一个char是否是数字或者字符
        int left = 0, right = s.length() - 1;
        while(left < right){
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
