package cn.ljava.jvm.loader;

import java.lang.reflect.Array;
import java.util.ArrayList;

/***
 * Java运行时一个类是什么时候被加载的
 */
public class Hello {
    //常量
    public static final int a = 123;
    public final int c = 234;
    public static final ArrayList<String> fArray = new ArrayList<>();
    //类变量
    public static int b;
    //实例变量
    public int abc;

    //  -XX:+TraceClassLoading 监控类的加载
    public static void main(String[] args){
        //User user = new User();
        //user.working();
        ArrayList<String> f1Array = new ArrayList<>();
        f1Array.add("c");
        f1Array.add("d");
        Hello hello = new Hello();
        fArray.add("a");
        fArray.add("b");
        //fArray = f1Array;
        fArray.forEach(f->{
            System.out.println(f);
        });
        Integer i1 = 127;
        int i2 = 233;
        System.out.println(i1 == i2);
        Integer i3 = 127;
        System.out.println(i1 == i3);
        Integer i4 = new Integer(123);
        Integer i5 = new Integer(123);
        System.out.println(i4 == i5);
        System.out.println(i4);
        System.out.println(i5);
    }
}
