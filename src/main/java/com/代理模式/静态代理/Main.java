package com.代理模式.静态代理;

import com.代理模式.JavaTool;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2021/3/1 17:24
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args){
        //可以用的编译器
        JavaTool javaTool = new JavaTool();
        //方便使用的编译器
        IdeaTool ideaTool = new IdeaTool(javaTool);
        //用idea代替txt编写java程序
        ideaTool.coding();
    }
}
