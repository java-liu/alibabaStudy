package com.ljava.somemethod.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型擦除
 * Java 泛型在编译时进行类型擦除，这意味着在运行时，泛型类型信息会被擦除，替换为它们的上限（通常是 Object）。因此，泛型主要在编译时提供类型检查，而在运行时并不保留具体的类型信息。
 * 类型擦除的影响:
 * 1.无法在运行时获取泛型类型信息：不能通过反射获取泛型参数的具体类型。
 * 2.不能实例化类型参数：不能直接使用 new T() 或 new T[]
 * 3.不能使用基本数据类型作为类型参数：必须使用包装类（如 Integer 而不是 int）。
 */
public class TypeErasureExample {
    public static void main(String[] args) {
        List<String> stringList =  new ArrayList<>();
        List<Integer>  integerList = new ArrayList<>();
        //尽管 stringList 和 intList 是不同类型的泛型列表，但在运行时它们的类类型是相同的。
        System.out.println(stringList.getClass() == integerList.getClass()); // true
    }
}
