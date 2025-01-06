package com.sanshao.service;

/**
 * @Description: 适配器设计模式介绍
 * 1、解决何种问题：解决接口下接口实现类之间继承矛盾问题
 * 2、适配器设计模式特征：
 *  1）使用抽象类分离了接口与【接口实现】
 *  2）抽象类分摊接口中需要常用的方法
 *  3）使得【接口实现类】可以随意选择接口中的方法来实现
 * 3、参考Servlet规范中的GenericServlet
 * @Author: Liuys
 * @CreateDate: 2020/6/14 21:55
 * @Version: 1.0
 */
public interface 学生守则接口 {
    /***
     * 考试后家长签字    ----- 不受实现类欢迎的方法
     */
    void signName();

    /***
     * 每周请代签的人看一次电影
     */
    void watchMovie();
}
