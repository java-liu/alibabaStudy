package com.exceptions;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.exceptions
 * @date 2020/1/17 10:13
 */
public class Test {
    public static void main(String[] args){
        Demo4 d = new Demo4();
        System.out.println(d.show(4));
    }
}

/***
 * 1、return返回之前会执行finally（不论是否出现异常）
 * 2、如果return放在finally中，那么最后返回的值就是finally中的值
 */
class Demo4{
    int show(int num){
        try {
            if(num < 0){
                throw new Exception();
            }
            return 10;
        }catch (Exception e){
            System.out.println(e.toString());
            //System.exit(0); //退出jvm虚拟机，finally也不会执行
            return 200;
        }finally {
            System.out.println("finally run");
            //return 100;
        }
    }
}