package com.sanshao.demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        /*Demo2 demo2 = new Demo2();
        demo2.getX();
        demo2.getDemo1Info();*/
        Test t = new Test();

        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        List<String> result = names.stream()
                .filter(getStr())
                .collect(Collectors.toList());
        result.forEach(System.out::println);

    }

    public static Predicate<String> getStr(){
        return str -> str.startsWith("A");
    }

}
