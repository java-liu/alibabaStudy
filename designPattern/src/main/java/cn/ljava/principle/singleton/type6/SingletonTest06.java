package cn.ljava.principle.singleton.type6;

public class SingletonTest06 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance1 = Singleton.INSTANCE;
        System.out.println(instance == instance1);

        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
        instance.sayOk();
        //Runtime就是一个单例类
        //Runtime runtime =
    }
}

/***
 * JDK 1.5中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。
 * 推荐使用
 */
enum Singleton{
    INSTANCE;
    public void sayOk(){
        System.out.println("OK");
    }
}
