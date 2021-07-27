package cn.ljava.utils;

public class SleepUtils {
    public static void sleep(int second){
        try{
            Thread.sleep(1000 * second);
        }catch (InterruptedException ie){
            Thread.currentThread().interrupt();
        }
    }
}
