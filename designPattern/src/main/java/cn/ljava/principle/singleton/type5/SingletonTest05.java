package cn.ljava.principle.singleton.type5;

public class SingletonTest05 {
}
class Singleton{
    private static volatile Singleton instance;

    private Singleton(){}

    private static class SingletonInstance{
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonInstance.INSTANCE;
    }
}