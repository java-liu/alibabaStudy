package com.ljava.somemethod.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型方法
 * 定义一个泛型方法 printArray(T[] array)，它可以打印任何类型的数组。
 */
public class Util {
    public static <T>  void printArray(T[] array){
        for (T element : array){
            System.out.println(element);
        }
    }

    /**
     * 上限通配符
     * @param numbers
     * @param <T>
     */
    public static <T>  void printNumbers(List< ? extends Number> numbers){
        for (Number number : numbers){
            System.out.println(number);
        }
    }

    public static <T> void addNumbers(List<? super Integer> numbers){
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }
    /**
     * 泛型方法示例
     * @param args
     */
    public static void main(String[] args) {
        // 创建一个 Integer 数组
        Integer[] intArray = {1, 2, 3, 4, 5};
        System.out.println("Integer Array:");
        printArray(intArray);

        // 创建一个 String 数组
        String[] strArray = {"Hello", "World", "Generics"};
        System.out.println("String Array:");
        printArray(strArray);

        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<String> strList = Arrays.asList("Hello", "World", "Generics");
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5);
        printNumbers(intList);
        //会报错,因为 List<String> 不是 Number 的子类
        //printNumbers(strList);
        printNumbers(doubleList);

        List<Number> numbers = new ArrayList<>();
        addNumbers(numbers);
        System.out.println(numbers);

        List<Object> objects = new ArrayList<>();
        addNumbers(objects);
        System.out.println(objects);
    }
}
