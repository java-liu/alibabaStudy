package com.sanshao.array;

/**
 * @Description: Java栈测试
 * @Author: Liuys
 * @CreateDate: 2021/3/8 13:56
 * @Version: 1.0
 */
public class StackTest {
    public static int count = 0;
    public static void test(){
        int a = 1,b = 2,c = 3, d = 4, e = 5;
        count++;
        test();
    }
    public static void main(String[] args){
        try{
            test();
        }catch (Throwable e){
            System.out.println("调用深度count=" + count);
            e.printStackTrace();
        }
    }
}
