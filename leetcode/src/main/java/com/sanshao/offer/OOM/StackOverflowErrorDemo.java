package com.sanshao.offer.OOM;

/**
 * @Description: 递归调用特别多，把栈空间给用完了
 * @Author: Liuys
 * @CreateDate: 2021/4/19 9:47
 * @Version: 1.0
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }
    private static void stackOverflowError(){
        stackOverflowError();//Exception in thread "main" java.lang.StackOverflowError
    }
}
