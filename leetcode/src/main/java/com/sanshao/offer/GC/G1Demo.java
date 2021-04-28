package com.sanshao.offer.GC;

import java.util.Random;

/**
 * @Description:
 * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 * @Author: Liuys
 * @CreateDate: 2021/4/23 17:15
 * @Version: 1.0
 */
public class G1Demo {
    public static void main(String[] args) {
        String str = "java";
        /*while (true){
            str += str +new Random().nextInt(7777777) + new Random().nextInt(888888);
            str.intern();
        }*/
        System.out.printf("%x\n",7252);
    }
}
