package com.ljava.somemethod.generics;

public class MultiBoundExample<T extends Comparable<T> & CharSequence> {
    private T content;

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public static void main(String[] args) {
        //
        MultiBoundExample<String> example = new MultiBoundExample<>();
        example.setContent("Hello");
        System.out.println("Content: " + example.getContent());

        //下面这行代码会导致编译错误,因为 Integer 只实现了 Comparable 接口,没有实现 CharSequence 接口
        //MultiBoundExample<Integer> integerExample = new MultiBoundExample<>();
    }
}
