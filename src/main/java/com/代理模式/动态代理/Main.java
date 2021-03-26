package com.代理模式.动态代理;

import com.代理模式.JavaTool;
import com.代理模式.Programmer;

import java.lang.reflect.Proxy;

/**
 * @Description: （原有的对象需要额外的功能，想想动态代理这项技术！）
 * Java提供了一个Proxy类，调用它的newInstance方法可以生成某个对象的代理对象,该方法需要三个参数：
 * 参数一：生成代理对象使用哪个类装载器【一般我们使用的是被代理类的装载器】
 * 参数二：生成哪个对象的代理对象，通过接口指定【指定要被代理类的接口】
 * 参数三：生成的代理对象的方法里干什么事【实现handler接口，我们想怎么实现就怎么实现】
 * 在编写动态代理之前，要明确几个概念：
 *
 * 代理对象拥有目标对象相同的方法【因为参数二指定了对象的接口，代理对象会实现接口的所有方法】
 * 用户调用代理对象的什么方法，都是在调用处理器的invoke方法。【被拦截】
 * 使用JDK动态代理必须要有接口【参数二需要接口】
 * 上面也说了：代理对象会实现接口的所有方法，这些实现的方法交由我们的handler来处理！
 *
 * 所有通过动态代理实现的方法全部通过invoke()调用
 *
 * 静态代理和动态代理的区别
 * 很明显的是：
 *
 * 静态代理需要自己写代理类-->代理类需要实现与目标对象相同的接口
 * 而动态代理不需要自己编写代理类--->(是动态生成的)
 * 使用静态代理时：
 *
 * 如果目标对象的接口有很多方法的话，那我们还是得一一实现，这样就会比较麻烦
 * 使用动态代理时：
 *
 * 代理对象的生成，是利用JDKAPI，动态地在内存中构建代理对象(需要我们指定创建 代理对象/目标对象 实现的接口的类型)，并且会默认实现接口的全部方法。
 * @Author: Liuys
 * @CreateDate: 2021/3/1 17:47
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args){
        //其他Java编译工具
        JavaTool javaTool = new JavaTool();

        Programmer programmerOtherTool = (Programmer) Proxy.newProxyInstance(javaTool.getClass().getClassLoader(), javaTool.getClass().getInterfaces(),((proxy, method, args1) -> {
            if(method.getName().equals("coding")){
                method.invoke(javaTool, args);
                System.out.println("其他JAVA编译工具！");
            }else{
                //如果不是调用coding方法，那么调用原对象的方法
                return method.invoke(javaTool, args);
            }
            return null;
        }));
        //
        programmerOtherTool.coding();
    }
}
