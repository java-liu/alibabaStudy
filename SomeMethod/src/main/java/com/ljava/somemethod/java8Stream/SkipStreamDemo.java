package com.ljava.somemethod.java8Stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SkipStreamDemo {
    public static void main(String[] args) {
        method1();
    }
    public static void method1(){
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);

        List<Integer> collect3 = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        System.out.println(collect3);
    }
}
