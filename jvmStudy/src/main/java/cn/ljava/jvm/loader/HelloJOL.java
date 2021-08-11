package cn.ljava.jvm.loader;


import org.openjdk.jol.info.ClassLayout;

/***
 * java内存布局  JOL java object layout
 *  <dependency>
 *             <groupId>org.openjdk.jol</groupId>
 *             <artifactId>jol-core</artifactId>
 *             <version>0.16</version>
 *         </dependency>
 */
public class HelloJOL {
    public static void main(String[] args) {
        Object o = new Object();
        //String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
