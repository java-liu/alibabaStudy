package com.ljava.somemethod.optionalMethod;

import java.util.Optional;
import java.util.stream.Stream;

/**
 *--------------------------
 * Optional.flatMap() 方法可以用于将 Optional 中的值转换为另一个 Optional 对象，或者将 Optional 中的值转换为一个流。
 *--------------------------
 * @author Liuys (开发人员)
 * @date 2024/4/30 (开发时间)
 * @version v1.0 (版本号)
 *-------------------------
*/
public class OptionalFlatmap {
    public static void main(String[] args) {
        System.out.println("Optional.flatMap() 方法可以用于将 Optional 中的值转换为另一个 Optional 对象，或者将 Optional 中的值转换为一个流。");


        /**
         *
         Optional.flatMap() 的用法
         Optional 是 Java 8 中引入的一个类，用于处理可能为 null 的值。Optional.flatMap() 方法可以用于将 Optional 中的值转换为另一个 Optional 对象，或者将 Optional 中的值转换为一个流。
         语法:
         Java
         <U> Optional<U> Optional<T>.flatMap(Function<? super T, Optional<? extends U>> mapper);
         请谨慎使用代码。
         content_copy
         参数说明:
         mapper: 一个函数，用于将 Optional 中的值转换为另一个 Optional 对象，或者将 Optional 中的值转换为一个流。
         返回值:
         如果 Optional 中存在值，则返回 mapper 函数应用于该值的结果；否则，返回一个空的 Optional 对象。
         */
        System.out.println("Optional.flatMap() 的用法");
        Optional<String> optional = Optional.ofNullable("Hello");
        Optional<Integer> strLength = optional.flatMap(str -> Optional.ofNullable(str.length()));
        if(strLength.isPresent()){
            System.out.println(strLength.get());
        }else{
            System.out.println("strLength is null");
        }
        //将 optional 中的字符串转换为大写
        Optional<String> strStream = optional.flatMap(str -> Optional.of(str.toUpperCase()));
        System.out.println(strStream.orElse("strStream is empty"));

        Optional<String> optional1 = Optional.empty();
        Optional<String>  result = optional1.flatMap(value -> Optional.of(value.toUpperCase()));
        System.out.println(result.orElse("result is empty"));
    }
}
