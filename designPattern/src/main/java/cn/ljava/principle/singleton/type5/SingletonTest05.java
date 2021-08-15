package cn.ljava.principle.singleton.type5;

/***
 * 静态内部类实现单例模式
 */
public class SingletonTest05 {
    public static void main(String[] args) {
        System.out.println(fun(4));
    }
    public static int fun(int n){
        if(n == 1){
            return 1;
        }
        return fun(n - 1) * n;
    }
}

class Singleton{
    private static volatile Singleton instance;

    private Singleton(){}

    /***
     * 当外部类被装载时，静态内部类不会被装载，
     * 需要实例化时，
     * 只有当调用getInstance时，静态内部类才会被装载，并且只会被装载一次，并且线程是安全的，
     * 类的静态属性只会在第一次加载类的时候初始化，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。
     * 优点：避免了线程不安全，利用静态内部类特点实现延迟加载，效率高
     */
    private static class SingletonInstance{
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonInstance.INSTANCE;
    }
}