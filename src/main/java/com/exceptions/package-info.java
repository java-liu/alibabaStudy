/**
 * @Description: 总结：
 * Java使用异常来表示错误，并通过try{...}catch{...}捕获异常
 * Java的异常是class，并且从Throwable继承
 * Error是无需捕获的严重错误
 * Exception是应该捕获的可处理的错误
 * RuntimeException无需强制捕获，非RuntimeException（CheckedException）需强制捕获，或者用throws声明
 * 可以有多个catch，catch的顺序非常重要：子类必须写在前面
 * 如果某些异常的处理逻辑相同：不存在继承关系，必须编写多条catch子句，可以用“|”表示多种Exception
 * 当某个方法抛出异常时：如果当前方法没有捕获，异常就被抛到上层调用方法，直到遇到某个try...catch被捕获，printStatckTrace()可以打印出方法的调用栈
 * 如何抛出异常：创建某个Exception的实例，用throw语句抛出
 * @Author: Liuys
 * @CreateDate: 2020/5/12 13:34
 * @Version: 1.0
 */
package com.exceptions;