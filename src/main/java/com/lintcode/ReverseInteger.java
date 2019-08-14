package com.lintcode;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.lintcode
 * @date 2019/8/12 10:00
 */
public class ReverseInteger {
    private static final Logger logger = LoggerFactory.getLogger(ReverseInteger.class);
    public static void main(String[] args){
        ReverseInteger ri = new ReverseInteger();
        //ri.reverseInteger(-458);
        //int x = -2333333333333;
        reverse(298745778);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    /**
    * 反转一个只有3位数的整数
    * @author      Liuys
    * @param       number
    * @return
    * @exception
    * @date        2019/8/12 10:18
    */
    public int reverseInteger(int number){
        int a = 0, b= 0, c = 0, reverse = 0;
        a = number/100;
        b = (number - a*100) / 10;
        c = number - a*100 -b*10;
        if(c != 0){
            reverse = c* 100;
        }
        if(b != 0){
            reverse += b * 10;
        }
        if(a != 0){
            reverse += a;
        }
        logger.info(String.valueOf(reverse));
        return reverse;
    }

    /***
     * 反转一个int类型的数
     * @param num
     * @return
     */
    public static int reverse(int num){
        int rev = 0;
        while(num != 0){
            int pop = num % 10;
            num = num / 10;
            /// 下面判断好像没用，定义int时，如果超过最大值和最小值，就报Error
            /*if(num > Integer.MAX_VALUE || num < Integer.MIN_VALUE){ return 0;}*/
            rev = rev * 10 + pop;
        }
        logger.info(String.valueOf(rev));
        return rev;
    }
}
