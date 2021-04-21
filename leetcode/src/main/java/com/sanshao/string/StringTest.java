package com.sanshao.string;


/**
 * @Description:
 * 在同包同类下, 引用自同一String对象.
 * 在同包不同类下,引用自同一String对象.
 * 在不同包不同类下,依然引用自同一String对象.
 * 在编译成.class时能够识别为同一字符串的,自动优化成常量,引用自同一String对象.
 * 在运行时创建的字符串具有独立的内存地址,所以不引用自同一String对象.
 * @Author: Liuys
 * @CreateDate: 2021/4/20 14:43
 * @Version: 1.0
 */
public class StringTest {
    public static void main(String[] args) {
        String hello = "Hello",lo = "lo";
        System.out.println(hello == "Hello");//true 在同包同类下, 引用自同一String对象.
        System.out.println(Other.hello == hello);//true 在同包不同类下,引用自同一String对象.
        System.out.println(com.sanshao.string.StringOther.hello == hello);//true 在不同包不同类下,依然引用自同一String对象.
        System.out.println(hello == ("Hel"+ "lo"));//true 在编译成.class时能够识别为同一字符串的,自动优化成常量,引用自同一String对象.
        System.out.println(hello == ("Hel" + lo));//false 在运行时创建的字符串具有独立的内存地址,所以不引用自同一String对象.
        System.out.println(hello == ("Hel" + lo).intern());//true
    }
}
class Other{
    static String hello = "Hello";
}
