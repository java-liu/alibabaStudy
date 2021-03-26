package com.代理模式.静态代理;

import com.代理模式.JavaTool;
import com.代理模式.Programmer;

/**
 * @Description: 相对于IdeaTool,如果是无参构造器，并且拿到JavaTool的对象，透明代理（普通代理）
 * @Author: Liuys
 * @CreateDate: 2021/3/1 17:30
 * @Version: 1.0
 */
public class IdeaTool1 implements Programmer {
    private JavaTool javaTool;

    /***
     * 真实对象（JavaTool）对外界来说是透明的
     */
    public IdeaTool1(){
        this.javaTool = new JavaTool();
    }

    public void writeCode(){
        System.out.println("用Idea编写JAVA代码更方便。。。。。");
    }
    /***
     * 程序员每天都要写代码
     */
    @Override
    public void coding() {
        //用TXT编写java代码
        javaTool.coding();
        /***
         * 用IDEA更方便
         */
        writeCode();
    }
}
