package com.ljava.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 目录的遍历
 */
public class FileList {
    public static void main(String[] args) throws IOException, ParseException {
        FileList fileList = new FileList();
        //fileList.demo1();
        File file = new File("info");
        //fileList.recursion(file);
        fileList.demo2();;
        LocalDate date = LocalDate.now();
        String dateStr = "20190312021234";
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);
        System.out.println(localDateTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = sdf.parse(dateStr);
        System.out.println(parse);
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        System.out.println(timeInMillis);
    }

    /**
     * 目录的遍历
     * public String list() :返回一个String数级,表示该File目录中的所有子文件或者目录
     * public File[] listFiles():  返回一个File数级,表示该File目录中的所有子文件或者目录
     */
    public void demo1(){
        File dir  = new File("info1");

        String[] names = dir.list();
        System.out.println(Arrays.toString(names));
        //List<String> list = Arrays.asList(names);  //如果目录不存在,会报空指针
        //list.forEach(System.out::println);

        //获取当前目录下的文件以及文件夹对象
        /**
         * listFiles在获取指定目录下的文件或者文件夹时必须满足下面两个条件:
         * 1.指定的目录必须存在
         * 2.指定的必须是目录,否则容易引发返回数组为null,出现NullPointException异常
         */
        File[] files = dir.listFiles();
        System.out.println(Arrays.toString(files)); //->[info/FileDemo2.txt, info/newDir]
        // info/FileDemo2.txt
        // info/newDir
        for (File file : files) {    //files可能为null
            System.out.println(file);
        }
    }

    public void demo2() throws IOException {
        File f1 = new File("info/newDir/新文件.txt");
        File f2 = new File("info/newDir2/新文件.txt");
        File f3 = new File("info/newDir", "新文件2.txt");
        if(f2.exists()){
            f2.delete();
        }
        Files.copy(f1.toPath(), f3.toPath());
        Files.copy(f1.toPath(), f2.toPath());
    }

    public void recursion(File file) throws IOException {
        //File file  = new File("info/newDir/新文件.txt");
        //System.out.println(file.exists());
        //System.out.println(file.createNewFile());
        //System.out.println(file.exists());
        //1.判断传入的是否是目录
        if(!file.isDirectory()){
            //不是目录直接返回
            return;
        }
        //2.已经知道传入的file是目录
        File[] files = file.listFiles();
        //遍历files
        for (File f : files) {
            //如果该目录下文件还是文件夹就再进行递归遍历其子目录
            if(f.isDirectory()){
                recursion(f);
            }else{
                //如果是文件
                System.out.println(f.getName());
            }
        }
    }
}
