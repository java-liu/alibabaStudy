package com.easyconding;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.easyconding
 * @date 2019/8/14 9:19
 */
public class ParamPassing {
    private static int intStatic = 222;
    private static String stringStatic = "old string";
    private static StringBuilder stringBuilderStatic = new StringBuilder("old stringBuilder");

    public static void main(String[] args){
        method(intStatic);
        method(stringStatic);
        method(stringBuilderStatic,stringBuilderStatic);

        System.out.println(intStatic);
        method();
        System.out.println(intStatic);
        System.out.println(stringStatic);
        System.out.println(stringBuilderStatic);
    }

    /***
     * A方法
     * @param intStatic
     */
    public static void method(int intStatic){
         /*有参的A方法字节码如下如示：
         1.SIPUSH 777
         2.ISTORE 0
         3.RETURN
         参数是局部变量，拷贝静态变量的777，并存入虚拟机栈的局部变量表的第一个小格子内。
         虽然在方法内部的intStatic与静态变量同名，但是因为作用域就近原则，它是局部变量的参数，
         所;有的操作与静态变量是无关的。
         */
        intStatic = 777;
    }

    /***
     * B方法
     */
    public static void method(){
        /*无参的B方法字节码如下如示：
        * 1.SIPUSH 888
        * 2.PUTSTATIC ParamPassing.intStatic:
        * 3.RETURN
        * 先把本地赋值的888压入虚拟机栈中的操作栈，然后给静态变量intStatic赋值。
        * */

        intStatic = 888;
    }

    /***
     * C方法
     * @param stringStatic
     */
    public static void method(String stringStatic){
        // String is immutable object,String没有提供任何方法用于修改对象
        stringStatic = "new string";
    }

    /***
     * D方法
     * @param stringBuilderStatic1
     * @param stringBuilderStatic2
     */
    public static void method(StringBuilder stringBuilderStatic1,StringBuilder stringBuilderStatic2){
        //直接使用参数引用修改对象
        stringBuilderStatic1.append(".method.first-");
        stringBuilderStatic2.append("method.second-");

        stringBuilderStatic1 = new StringBuilder("new stringBuilder");
        stringBuilderStatic1.append("new method's append");
    }
}
