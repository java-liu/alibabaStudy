package com.exceptions;

/**
 * 蓝屏异常，可以声明，让调用者给出处理方式
 * @author Liuys
 * @version V1.0
 * @Package com.exceptions
 * @date 2020/1/16 15:07
 */
public class BlueScreenException extends Exception {
    public BlueScreenException(){
        super();
    }
    public BlueScreenException(String msg){
        super(msg);
    }
}
