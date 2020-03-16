package com.exceptions;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.exceptions
 * @date 2020/1/17 10:13
 * try catch finally组合方式：
 * 1、try catch:对代码进行异常检测，并对检测的异常传递给catch处理。异常捕获处理
 * void show(){//不需要throws
 *     try{
 *          throw new Exception();
 *     }catch(Exception e){
 *
 *     }
 * }
 * 2、try finally：对代码进行异常检测，检测到异常后因为没有catch，所以一样会被默认jvm抛出。
 * 异常是没有捕获处理的。但是功能所开启的资源需要进行关闭，所以有finally。只为关闭资源。（或者释放资源）
 * void show() throws Exception{
 *      try{
 *          throw new Exception();
 *      }finally{
 *
 *      }
 * }
 * 3、try catch finally
 * 检测异常，并传递给catch处理，并关闭资源
 * 4、try catch1 catch2 catch3
 * */
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