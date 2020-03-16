package com.exceptions;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.exceptions
 * @date 2020/1/17 14:05
 * 异常在继承或者实现中的使用细节
 * Exception
 *  AException
 *      AAException
 *  BException
 *      BBException
 * 1、子类覆盖父类方法时，如果父类的方法声明异常，子类只能抛出父类异常或者该异常的子类，或者不抛。
 * 2、当父类方法声明多个异常时，子类覆盖时只能声明多个异常的子集。
 * 3、当被覆盖的方法没有异常声明时，子类覆盖时无法声明异常的。
 * 举例：父类存在这种情况，接口也有这种情况，
 * 问题：接口中没有声明异常，而实现的子类覆盖方法时发生了异常，怎么办？
 * 无法进行throws声明，只能catch的捕获，万一问题处理不了，catch中继续throw抛出，但是只能将异常转换成RuntimeException子类抛出。
 * Interface Inter{
 *     void show();
 * }
 * class Demo implements Inter{
 *     public void show(){//不能throws Exception
 *         try{
 *             throw new Exception();
 *         }catch(Exception e){
 *             .....
 *             throw new RuntimeException("");
 *         }
 *     }
 * }
 */
public class Test2 {
}
