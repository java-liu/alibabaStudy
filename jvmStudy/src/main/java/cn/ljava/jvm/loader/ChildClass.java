package cn.ljava.jvm.loader;


public class ChildClass extends Test02 {
    //静态常量   ==准备阶段赋值
    public static final String STATIC_FINAL = "静态常量";
    //静态变量  ----准备阶段赋值为null，初始化阶段赋值为 “静态变量”
    public static String STATIC_VARIATE = "静态变量";
    //变量   --创建对象的时候赋值
    public String filed = "变量";

    //静态初始化块  ---初始化阶段执行
    static {
        System.out.println("静态初始化块");
        System.out.println(STATIC_FINAL);
        System.out.println(STATIC_VARIATE);
    }
    //初始化块 ---创建对象的时候执行
    {
        System.out.println("初始化块");
        System.out.println(filed);
    }
    //构造器   ---创建对象的时候执行
    public ChildClass(){
        super();
        System.out.println("构造器");
    }

    public static void main(String[] args) {
        new Test02();
    }
}
