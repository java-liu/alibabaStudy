package com.sanshao.control;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2020/6/16 13:59
 * @Version: 1.0
 */
public class TwoServlet extends GenericServlet {
    /***
     * service在GenericServlet中是抽象方法，子类必须要实现（重写）
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //1、从协议包【请求行】来读取浏览器发送的请求方式

        //下面这是一段重复性代码。
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        if("GET".equals(method)){
            doGet(servletRequest, servletResponse);
        }else if("POST".equals(method)){
            doPost(servletRequest, servletResponse);
        }
    }

    public void doPost(ServletRequest arg0, ServletResponse arg1){
        System.out.println("doPost is running......");
    }

    public void doGet(ServletRequest arg0, ServletResponse arg1){
        System.out.println("doGet is running......");
    }
}
