package com.ljava.file;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * FileOutputStream相关的构造方法
 * 1.使用FileOutputStream(File file)
 * 如果file不存在,则会报 Exception in thread "main" java.io.FileNotFoundException
 * 2.使用FileOutputStream(String path)
 * 如果path相关的文件不存在,则会创建,如果存在,会清空这个文件的数据.
 * 如果多级目录,则不会自动创建文件,报 Exception in thread "main" java.io.FileNotFoundException
 */
public class FileOutputStreamConstructor {
    public static void main(String[] args) throws IOException {
        //demo2();
        //demo3();
        //demo4();
        demo5();
    }

    /**
     * FileOutputStream构造方法
     * @throws FileNotFoundException
     */
    public static void demo1() throws FileNotFoundException {
        // 使用File对象创建流对象
        //Exception in thread "main" java.io.FileNotFoundException: info/newDir3/自动创建.txt (No such file or directory)
        //File file = new File("info/newDir3/自动创建.txt");
        //FileOutputStream fos = new FileOutputStream(file);
        //使用文件名称创建流对象
        FileOutputStream fos1 = new FileOutputStream("info/newDir3/自动创建文件.txt");
    }

    /**
     * 使用FileOutputStream写出字节数据主要通过Write方法,
     * 1.public void write(int b)
     * 2.public void write(byte[] b)
     * 3.public void write(byte[]b, int off, int len) 从 off索引开始,写出 len个字节
     */
    public static void demo2() throws IOException {
        File file = new File("info/newDir3/fos.txt");
        System.out.println(file.exists());
        file.mkdirs();
        FileOutputStream fos = new FileOutputStream("info/newDir3/fos.txt");
        //写出数据
        fos.write(97);
        fos.write(98);
        fos.write(99);
        /**
         * 虽然参数为int类型四个字节,但是只会保留一个字节的信息写出
         * 流操作完毕后,必须释放系统资源,调用close方法.
         */
        //close()
        fos.close();
    }

    /**
     * 写出字节数组 write(byte[] b), write array
     */
    public static void demo3() throws IOException {
        File file = new File("info/newDir3/fox.txt");
        //file.mkdirs();
        file.createNewFile();
        //多层次文件目录不会创建,会报异常
        FileOutputStream fos = new FileOutputStream("info/newDir3/fox.txt");
        byte[] b = "java要好好学".getBytes(StandardCharsets.UTF_8);
        fos.write(b);
        fos.close();
    }

    /**
     * write(byte[] b, int off, int len) write array to file from from to from+to
     * 每次写出从off索引开始,len个字节
     * @throws IOException
     */
    public static void demo4() throws IOException {
        File file = new File("info/newDir3/demo4.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        byte[] b = "this is a new file".getBytes(StandardCharsets.UTF_8);
        //from index=4 to 4+10
        fos.write(b,4,10);
        fos.close();
    }

    public static void demo5(){
        byte[] b = "this is a new file".getBytes(StandardCharsets.UTF_8);
        byte[] b2 = "这是中文,需要怎么处理呢,这是个问题,中华人民共和国,新文件,为什么是负数呢,需要理解.".getBytes(StandardCharsets.UTF_8);
        int i = 0;
        for (byte b1 : b2) {
            System.out.println(b1);
            System.out.println(b1+ "的索引为"+ i);
            i++;
        }
        byte[] b3 = "%".getBytes(StandardCharsets.UTF_8);
        System.out.println(b3.length);

    }
}
