package com.exceptions;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.exceptions
 * @date 2020/1/15 16:07
 * @description
 * 1.RuntimeException是那些可能在Java虚拟机正常运行期间抛出的异常的超类，
 * 可能在执行方法期间抛出但未被捕获的 RuntimeException的任何子类都无需在 throws 子句中进行声明。
 * 为什么抛出RuntimeException，不需要捕获，不要声明呢？
 * 不是功能本身发生的异常，而是因为比如调用者传递参数错误而导致功能运行失败，
 * 这时也是问题，需要通过异常来体现，但是这个异常不要声明出来的。
 * 声明的目的是为了让调用者进行处理。
 * 不声明的目的是不让调用者进行处理，就是为了让程序停止，让调用者看到现象，并进行代码的修正。
 *
 *
 *原理异常分两种：
 * 1、编译时异常：编译器会检测的异常
 * 2、运行时异常：编译器不会检测的异常，不需要声明。声明也可以，如果声明了，无外乎就是让调用者给出处理方式。
 *throw和throws的区别：
 * 1、throw用在函数内。throws用在函数上。
 * 2、throw抛出的异常对象。 throws用于进行异常类的声明，后面异常类可以有多个，用逗号隔开。
 */
public class MyException extends RuntimeException{
    //定义构造函数
    public MyException(){
        super();
    }
    public MyException(String message){
        //如果自定义异常需要异常信息，可以通过调用父类的带有字符串参数的构造函数即可
        super(message);
    }
}
