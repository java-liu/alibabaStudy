###1.1 什么是IO
数据的传输，可以看做是一种数据的流动，按照流动的方向，以内存为基准，分为输入`input` 和输出`output` ，即流向内存是输入流，流出内存的输出流。

Java中I/O操作主要是指使用`java.io`包下的内容，进行输入、输出操作。输入也叫做读取数据，输出也叫做作写出数据。
###1.2 IO的分类
根据数据的流向分为:`输入流`和`输出流`
+ ``输入流:``把数据从`其他设备`上读取到`内存`中的流
+ ``输出流:``把数据从`内存`中写出到`其中设备`上的流

根据数据的类型分为:`字节流`和`字符流`
+ ``字节流:``以字节为单位,读写数据的流
+ ``字符流:``以字符为单位,读写数据的流

分类之后对应的父类

||输入流|输出流|
|---|---|---|
|字节流|字节输入流InputStream|字节输出流OutputStream|
|字符流|字符输入流Reader|字符输出流Writer|

>由这四个类的子类名称基本都是以其父类名作为子类名的后缀
> 
> 如:InputStream的子类FileInputStream
> 
> 如:Reader的子类FileReader


内存("abc"):临时存储--->(输出):把内存中的数据,写入到硬盘中保存 (硬盘)a.txt "abc"

硬盘(永久存储):--->(输入)把硬盘中的数据,读取到内存中使用
i:input输入(读取)

o:output输出(写入)

流:数据(字符,字节) 1个字符=2个字节 1个字节=8个二进制位

>流根据方向可以分为:输入流和输出流.注意,输入和输出是相对于内存而言的.从内存出来就是输出,到内存中就是输入.输入流又叫做InputStream,输出流又叫做OutputStream,输入还叫做"读Read",输出还叫做"写Write"

字节流适合读取:视频,声音,图片等二进制文件.

字符流适合读取:纯文本文件.

![IO流](./assets/认识IO-1652247003618.png)
![IO继承关系](./assets/认识IO-1652254826633.png)

### 2.1文件的世界里一切皆为字节
一切文件数据(文本,图片,视频)在存储时,都是以二进制数字的形式保存,都是一个一个的字节,那么传输时一样如此.所以,字节流可以传输任意文件数据.在操作流的时候,我们要时刻明确,无论使用什么样的对象,底层传输的始终为二进制数据.

### 2.2字节输出流 (OutputStream)
`java.io.OutputStream`抽象类是表示字节输出流的所有类的超类,将指定的字节信息写出到目的地.它定义了字节输出流的基本共性功能方法.

**字节输出流的基本共性功能方法**

+ `public void close():`关闭此输出流并释放与此流相关联的任何系统资源
+ `public void flush():`刷新此输出流并强制任何缓冲的输出字节被写出.
+ `public void write(byte[] b):`将b.length个字节从指定的字节数组写入此输出流
+ `public void wirte(byte[] b, int off, int len):`从指定的字节数组写入len字节,从偏移量off开始输出到此输出流.也就是说从off个字节数开始读取一直到len个字节结束
+ `public abstract void write(int b:)`将指定的字节输出流
**以上五个方法则是字节输出流都具有的方法,由父类OutputStream定义提供,子类都会共享以上方法.**

#### FileOutputStream类
`OutputStream`有很多子类,从最简单的一个子类FileOutputStream开始.看名字就知道是文件输出流,用于将数据写出到文件.

#### FileOutputStream构造方法
+ `public FileOutputStream(File file):`根据File对象为参数创建对象
+ `public FileOutputStream(String name):`根据名称字符串为参数创建对象

#### 推荐第二种构造方法:
```java
FileOutputStream outputStream = new FileOutputStream("abc.txt");
```
就以上面这句代码来讲,类似这样创建字节输出流对象都做了三件事情:
+ 调用系统功能去创建文件[输出流对象才会怎么创建]
+ 创建outputStream对象
+ 把outputStream对象指向这个文件
>注意:
> 创建输出流对象的时候,系统会自动去对应位置创建对应文件,而创建输出流对象的时候,文件不存在则报FileNotFoundException异常,也就是系统找不到指定的文件异常.

当创建一个流对象时,必须直接或者间接传入一个文件路径.比如现在创建一个 `FileOutputStream`流对象,在该路径下,如果没有这个文件,会创建这个文件.如果有这个文件,会清空这个文件的数据.

```java
import java.io.FileOutputStream;

public class FileOutputStreamTest() {
    public static void main(String[] args) {
        // 使用File对象创建流对象
        File file = new File("info/newDir3/自动创建.txt");
        FileOutputStream fos = new FileOutputStream(file);
        //使用文件名称创建流对象
        FileOutputStream fos1 = new FileOutputStream("info/自动创建文件.txt");
    }
}
```

