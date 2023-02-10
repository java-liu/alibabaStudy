package com.ljava.file;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * File对文件本身进行操作,并不能对文件内容进行操作
 * 跟流无关,也就是不能对文件进行读和写也就是不能进行输入和输出
 * File 类的注意点:
 * 1.一个 File对象代表硬盘中实际存在的一个文件或者目录
 * 2.File 类的构造方法不会校验这个文件或者文件夹是否真实存在,因此无论如该路径下是否存在文件或者目录,都不影响 File对象的创建
 */
public class FileInfo {
    private static final Logger log = LogManager.getLogger();
    /**
     * 常用方法
     * 1.获取功能的方法
     * getAbsolutePath():返回此File 的绝对路径名字符串
     * getPath(): 返回此 File转换为路径名字符串
     * getName():返回此 File表示的目录或文件的名称
     * length():返回此 File表示的文件的长度
     */
    public void demo1(){
        /**
         * 绝对路径:完整的路径
         * 相对路径:相对于当前文件(IOStudy)的路径
         */
        File file = new File("README.md");
        System.out.println("此文件的绝对路径为:" + file.getAbsolutePath());
        System.out.println("此文件的构造路径为:" + file.getPath());
        System.out.println("此文件的名称为:" + file.getName());
        System.out.println("此文件的长度为:" + file.length() + "字节");

        //
        File file1 = new File("info");
        System.out.println("此目录的绝对路径为:" + file1.getAbsolutePath());
        System.out.println("此目录的构造路径为:" + file1.getPath());
        System.out.println("此目录的名称为:" + file1.getName());
        System.out.println("此目录的长度为:" + file1.length());

        log.info("此目录的长度为:{}",  file1.length());
        log.error("出现错误", "error");

    }

    public void demo2(){
        /**
         * 绝对路径
         */
        File file = new File("/Users/liuys/IdeaProjects/alibabaStudy/README.md");
        System.out.println("此文件的绝对路径为:" + file.getAbsolutePath());

        /**
         * 相对路径,项目下的FileDemo2.txt
         */
        File file1 = new File("info/FileDemo2.txt");
        System.out.println("file1的绝对路径为:" + file1.getAbsolutePath());
        System.out.println("file1的相对路径为:" + file1.getPath());
    }

    /**
     * 判断功能的方法
     * public boolean exists():此File表示的文件或者目录是否实际存在
     * public boolean isDirectory(): 此File表示的是否为目录
     * public boolean isFile(): 此File表示的是否为文件
     */
    public void demo3(){
        File f = new File("/Users/liuys/IdeaProjects/alibabaStudy/README.md");
        File f2 = new File("info");

        //判断是否存在
        System.out.println("README.md 是否存在:" + f.exists());
        System.out.println("info 是否存在:" + f.exists());

        //判断是文件还是目录
        System.out.println("info 目录?" + f2.isDirectory());
        System.out.println("info 文件?" + f2.isFile());
    }

    /**
     * 创建删除功能的方法
     * createNewFile() : 文件不存在,创建一个新的空文件并返回 true, 文件存在,不创建文件并返回false
     * delete() : 删除由此File表示的目录或者文件
     * mkdir() : 创建由此File表示的目录
     * mkdirs(): 创建由此File表示的目录,包括任何必需但不存在的父目录
     * 其中,mkdir()和mkdirs()方法类似,但mkdir(),只能创建一级目录,mkdirs()可以创建多级目录比如//a//bb/ccc,所以开发中一般使用mkdirs()
     */
    public void demo4() throws IOException {
        //文件的创建
        File f = new File("info/fileDemo4.txt");
        System.out.println("是否存在:" + f.exists());
        System.out.println("是否创建:" + f.createNewFile());
        System.out.println("是否创建:" + f.createNewFile()); //已经创建过了所以再调用createNewFile返回false
        System.out.println("是否存在:" + f.exists());

        //多级目录的创建
        File f2 = new File("info/newDir/");
        System.out.println("是否存在:" + f2.exists());
        System.out.println("是否创建:" + f2.mkdirs());
        System.out.println("是否存在:" + f2.exists());
        File f4 = new File("\\info\\newDir"); //Mac下创建的不是多级目录
        System.out.println("是否存在:" + f4.exists());
        System.out.println("是否创建:" + f4.mkdirs());

        //目录的创建
        File f3 = new File("info1");
        System.out.println("是否存在:" + f3.exists());
        System.out.println("是否创建:" + f3.mkdirs());
        System.out.println("是否存在:" + f3.exists());

        //文件的删除
        System.out.println(f.delete()); //true
        //目录的删除
        System.out.println(f3.delete()); //true
        //f3.deleteOnExit();
        System.out.println(f4.delete()); //true
    }


    public static void main(String[] args) throws IOException {
        FileInfo fileInfo = new FileInfo();
        fileInfo.demo1();
        //fileInfo.demo2();
        //fileInfo.demo3();
        //fileInfo.demo4();
    }
}
