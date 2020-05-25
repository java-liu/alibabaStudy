package com.exceptions;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.exceptions
 * @date 2020/1/16 9:50
 * 1、声明和捕获
 * （1）声明：将问题标识出来，报告给调用者
 * 如果函数内能过throw抛出了编译时异常，而捕获，那么必须通过throws进行声明，让调用者去处理
 * （2）捕获：java中对异常有针对性的语句进行捕获。
 * try{
 *      //需要被检测的语句
 * }catch(异常类 变量){
 *      //异常的处理语句
 * }finally{
 *     //一定会被执行的语句
 * }
 *
 * 如果定义功能时有问题发生需要报告给调用者，可以通过在函数上使用throws关键字进行声明
 */
public class Demo {
    public static void main(String[] args)//2、或者捕获 throws Exception//1、在调用者上声明
    {
        ExceptionDemo1 d1 = new ExceptionDemo1();
        try{
            d1.show();//当调用了声明异常的方法时，必须有处理方式，要么捕获，要么声明
        }catch (Exception e){//Exception e  = new Exception()、括号中需要定义什么呢？对方抛出的是什么问题，在括号中就定义什么问题的引用
            System.out.println("异常发生了！");
        }
        System.out.println("Hello World!");
    }
}

class ExceptionDemo1{
    void show() throws Exception{
        throw new Exception();
    }
}
