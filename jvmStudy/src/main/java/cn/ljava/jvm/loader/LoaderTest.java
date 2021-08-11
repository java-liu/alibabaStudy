package cn.ljava.jvm.loader;

public class LoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = Hello.class.getClassLoader();
        System.out.println(loader);
        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
        //loader.loadClass("cn.ljava.jvm.loader.Test02");
        //使用Class.forName()来加载类，默认会执行初始化块
                //Class.forName("cn.ljava.jvm.loader.Test02");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
                Class.forName("cn.ljava.jvm.loader.Test02", true, loader);
    }
}


