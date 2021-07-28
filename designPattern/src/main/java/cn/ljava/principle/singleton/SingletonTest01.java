package cn.ljava.principle.singleton;


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
//饿汉式
class Singleton{
    //1、构造器私有化，外部不能new
    private Singleton(){

    }
    //2、本类内部创建对象实例
    private final static Singleton instance = new Singleton();

    //3、提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance(){
        return instance;
    }
}