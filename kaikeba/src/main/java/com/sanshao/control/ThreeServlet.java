package com.sanshao.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: HttpServlet就体现了模板设计模式，只需要重写相对就的doGet\doPost\doPut......等需要自己实现的方法就行。
 * 最后tomcat会执行service方法（HttpServlet中已经定义，程序员不需定义，也不需要关心调用顺序，只需要处理自己的实现就行）后，就会执行子类中对应的已经重写的方法
 * @Author: Liuys
 * @CreateDate: 2020/6/16 14:30
 * @Version: 1.0
 */
public class ThreeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ThreeServlet doGet is running......");
    }
}
