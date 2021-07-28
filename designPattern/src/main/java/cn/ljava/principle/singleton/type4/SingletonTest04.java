package cn.ljava.principle.singleton.type4;

public class SingletonTest04 {
}

//懒汉式（线程安全，同步方法）
// Double-Check 推荐使用
class Singleton{
    private static volatile Singleton instance;
    private Singleton(){}
    //提供一个静态的公有方法，加入双重检查代码，解决线程安全问题，同时解决懒加载问题
    //同时保证效率
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}