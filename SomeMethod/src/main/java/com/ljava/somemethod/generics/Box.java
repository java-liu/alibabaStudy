package com.ljava.somemethod.generics;

/**
 * 泛型类
 * 定义一个泛型类 Box<T>，它可以存储任何类型的对象。
 */
public class Box<T> {
    private T content;

    public void setContent(T content) {
        this.content = content;
    }
    public T getContent() {
        return content;
    }

    /**
     * 通配符`?`表示未知类型
     * @param box
     */
    public static void printBoxContent(Box<?> box) {
        System.out.println("Box Content: " + box.getContent());
    }

    public static void Swap(int a,int b){
        int c=a;
        a=b;
        b=c;
        System.out.println("a: "+a);
        System.out.println("b: "+b);
    }

    public static void Sample(int a){
        a+=20;
        System.out.println("a: "+a);
    }

    public static void main(String[] args) {
        int b=10;
        Sample(b);
        System.out.println("b: "+b);


        int c=10;
         int d=20;
         Swap(c,d);
         System.out.println("After Swap:");
         System.out.println("c: "+c);
        System.out.println("d: "+d);
        // 创建一个 Box 对象来存储 String 类型的对象
        Box<String> stringBox = new Box<>();
        stringBox.setContent("Hello, World!");
        System.out.println("String Box Content: " + stringBox.getContent());

        // 创建一个 Box 对象来存储 Integer 类型的对象
        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(123);
        System.out.println("Integer Box Content: " + integerBox.getContent());

        printBoxContent(stringBox);
        printBoxContent(integerBox);
    }
}
