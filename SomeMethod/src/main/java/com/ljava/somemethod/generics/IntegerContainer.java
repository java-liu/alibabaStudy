package com.ljava.somemethod.generics;

public class IntegerContainer implements Container<Integer>{

    private Integer content;
    @Override
    public void add(Integer item) {
        this.content = item;
    }
    @Override
    public Integer get() {
        return content;
    }

    public static void main(String[] args) {
        // 创建一个 IntegerContainer 对象
        Container<Integer> integerContainer = new IntegerContainer();
        integerContainer.add(123);
        System.out.println("Integer Container Content: " + integerContainer.get());
    }
}
