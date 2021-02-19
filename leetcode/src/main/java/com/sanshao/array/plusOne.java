package com.sanshao.array;


import java.util.Arrays;

/**
 * @Description: 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: Liuys
 * @CreateDate: 2021/2/18 9:33
 * @Version: 1.0
 */
public class plusOne {
    public static int[] plusOne1(int[] digits){
        if(digits.length == 0) return new int[0];
        int len = digits.length;
        //将原数组复制len长度到新数组，对原数组没有影响
        int[] targetArray = Arrays.copyOf(digits, len);
        //最后一位小于9，直接加1
        if(digits[len - 1] < 9){
            targetArray[len - 1] = digits[len - 1] + 1;
            return targetArray;
        }else{
            //如果全部为9，长度要加1
            boolean flag = true;
            for (int digit : digits) {
                if(digit != 9){
                    flag = false;
                }
            }
            //flag = true，全部为9，数组长度+1
            if(flag){
                //首位为1，其他为0
                int[] targetArray1 = new int[len + 1];
                targetArray1[0] = 1;
                return targetArray1;
            }else{
                //不全部为9，数组长度不变
                //1、如果最后一位为9，需要进一位
                if(digits[len - 1] == 9){
                    for(int i = len -1; i>=0; i--){
                        if(digits[i] == 9){
                            targetArray[i] = 0;
                            targetArray[i-1] = digits[i-1] + 1;
                        }else{
                            break;
                        }
                    }
                }
            }
        }

        return targetArray;
    }

    /***
     * 根据题意加一，没错就是加一这很重要，因为它是只加一的所以有可能的情况就只有两种：
     *
     * 除 99 之外的数字加一；
     * 数字 99。
     * 加一得十进一位个位数为 00 加法运算如不出现进位就运算结束了且进位只会是一。
     *
     * 所以只需要判断有没有进位并模拟出它的进位方式，如十位数加 11 个位数置为 00，如此循环直到判断没有再进位就退出循环返回结果。
     *
     * 然后还有一些特殊情况就是当出现 9999、999999 之类的数字时，循环到最后也需要进位，出现这种情况时需要手动将它进一位。
     *
     * @param digits
     * @return
     */
    public static int[] plusOne2(int[] digits){
        for(int i = digits.length -1 ;i >= 0; i--){
            digits[i]++;
            digits[i] = digits[i]%10;
            if(digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args){
        int[] digits = {0};
        Commons.print(plusOne2(digits));
    }
}
