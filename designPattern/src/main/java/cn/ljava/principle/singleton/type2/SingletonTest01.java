package cn.ljava.principle.singleton.type2;


/***
 * 单例
 */
public class SingletonTest01 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton == singleton1);
        System.out.println(singleton.hashCode());
        System.out.println(singleton1.hashCode());
    }

}

//饿汉式（静态代码块）
class Singleton{
    private static Singleton instance;
    //1、构造器私有化，外部不能new
    private Singleton(){

    }
    //在静态代码块中，创建单例对象
    static{
        instance = new Singleton();
    }

    //3、提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance(){
        return instance;
    }
}