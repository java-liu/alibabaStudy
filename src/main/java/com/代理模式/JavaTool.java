package com.代理模式;

/**
 * @Description: Programmer实现类
 * @Author: Liuys
 * @CreateDate: 2021/3/1 17:10
 * @Version: 1.0
 */
public class JavaTool implements Programmer {


    /***
     * 程序员每天都要写代码
     */
    @Override
    public void coding() {
        System.out.println("用TXT文档编写JAVA代码。。。。。。。");
    }

    public void coding1(){
        System.out.println("用另外文档编写JAVA代码。。。。。。。");
    }
}
