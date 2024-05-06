package com.ljava.somemethod.optionalMethod;

import java.util.Optional;

/**
 *--------------------------
 * use map() 将 Optional的值映射到另外一个值
 *--------------------------
 * @author Liuys (开发人员)
 * @date 2024/4/30 (开发时间)
 * @version v1.0 (版本号)
 *-------------------------
*/
public class OptionalMap {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("hello");

        Optional<Integer> length = optional.map(String::length); //length 为 Optional.of(5)

        System.out.println(length);
        System.out.println(length.get());
    }
}
