package com.ljava.somemethod.optionalMethod;

import java.util.Optional;

public class OptionalFilter {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("ljava");

        //predicate: 一个断言函数，用于判断 Optional 中的值是否满足条件。
        Optional<String> optional1 = optional.filter(s -> s.toUpperCase().equals("LJAVA"));
        //如果 Optional 中的值满足条件，则返回一个包含该值的 Optional 对象；否则，返回一个空的 Optional 对象。
        System.out.println(optional1.orElse("111"));

        Optional<String> optional2 = optional.filter(s -> s.length() == 0);
        if(optional2.isPresent()){
            System.out.println("true");
        }else{
            System.out.println("false");
        }

        //value 不能为null
        //Optional<String> optional3 = Optional.of(null); //--空指针异常
        Optional<String> optional3 = Optional.of("");
        Optional<String> optional4 = optional3.filter(s -> s == null);
        if(optional4.isPresent()){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
