package com.lintcode;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.lintcode
 * @date 2019/8/9 15:38
 */
public class SingleNumber {
    public static void main(String[] args){
        int[] A = {3,3,2,2,5,4,4};
        System.out.println(singleNumber(A));
    }
    public static int singleNumber(int[] A){
        if(A ==null || A.length ==0){
            return -1;
        }
        int rst = 0;
        for(int i = 0; i < A.length; i++){
            rst = rst ^ A[i];
        }
        return rst;
    }
}
