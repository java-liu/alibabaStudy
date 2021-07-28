package cn.ljava.principle.singleton.type3;


public class SingletonTest03 {
    public static void main(String[] args) {

    }
}
//懒汉式（线程不安全）,不要使用这种方式
/*
class Singleton{
    private static Singleton instance;
    private Singleton(){}


    //当调用getInstance 才创建单例对象，饿汉式
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}*/
//懒汉式，线程安全，同步方法
class Singleton{
    private static Singleton instance;
    private Singleton(){}


    //当调用getInstance 才创建单例对象，饿汉式
    //效率太低了，每个线程在想获得类的实例时候，执行getInstance方法都要进行同步。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接return就行了。
    //方法进行同步效率太低
    /*public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }*/
    public static Singleton getInstance(){
        synchronized (Singleton.class){
            if(instance == null){
                instance = new Singleton();
            }
        }
        return instance;
    }
}