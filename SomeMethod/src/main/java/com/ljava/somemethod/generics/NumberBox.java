package com.ljava.somemethod.generics;

/**
 * 泛型边界
 * 限制类型参数的范围,可以使用 extends 关键字来指定类型参数的上限.
 * @param <T>
 */
public class NumberBox<T extends Number> {
    private T content;

    public void setContent(T content) {
        this.content = content;
    }
    public T getContent() {
        return content;
    }

    public static void main(String[] args) {
        NumberBox<Integer> integerNumberBox = new NumberBox<>();
        integerNumberBox.setContent(123);
        System.out.println("Integer Number Box Content: " + integerNumberBox.getContent());

        NumberBox<Double>  doubleNumberBox = new NumberBox<>();
        doubleNumberBox.setContent(3.14);
        System.out.println("Double Number Box Content: " + doubleNumberBox.getContent());

        //下面这行代码会导致编译器报错,因为 String 不是 Number 的子类
        //NumberBox<String> stringNumberBox = new NumberBox<String>();
    }
}
