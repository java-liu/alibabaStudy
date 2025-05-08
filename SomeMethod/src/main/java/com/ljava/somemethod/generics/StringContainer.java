package com.ljava.somemethod.generics;

/**
 * 泛型接口的实现
 */
public class StringContainer implements Container<String>{

    private String content;
    @Override
    public void add(String item) {
        this.content = item;
    }
    @Override
    public String get() {
        return content;
    }

    public static void main(String[] args) {
        // 创建一个 StringContainer 对象
        Container<String> stringContainer = new StringContainer();
        stringContainer.add("Hello, Generics!");
        System.out.println("String Container Content: " + stringContainer.get());
    }
}
