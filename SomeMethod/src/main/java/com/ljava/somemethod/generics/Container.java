package com.ljava.somemethod.generics;

/**
 * 泛型接口
 * 定义一个泛型接口 Container<T>
 * @param <T>
 */
public interface Container<T> {
    void add(T item);
    T get();
}
