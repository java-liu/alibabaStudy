package com.代理模式.静态代理;

import com.代理模式.JavaTool;
import com.代理模式.Programmer;

/**
 * @Description: Programmer实现类，普通代理
 * @Author: Liuys
 * @CreateDate: 2021/3/1 17:13
 * @Version: 1.0
 */
public class IdeaTool implements Programmer {

    private JavaTool javaTool;

    public IdeaTool(JavaTool javaTool){
        this.javaTool = javaTool;
    }

    /***
     * 代理类自定义方法
     */
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
