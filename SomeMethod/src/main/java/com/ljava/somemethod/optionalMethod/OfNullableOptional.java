package com.ljava.somemethod.optionalMethod;

import java.util.Optional;

/**
 *--------------------------
 * 可以使用 orElse() 或 orElseGet() 方法处理 Optional 为空的情况.
 * 这两个方法都接受一个默认值参数,如果 Optional 为空,则返回该默认值
 *--------------------------
 * @author Liuys (开发人员)
 * @date 2024/4/30 (开发时间)
 * @version v1.0 (版本号)
 *-------------------------
*/
public class OfNullableOptional {
    public static void main(String[] args)
    {
        Optional<String> optional = Optional.empty();
        String value = optional.orElse("World");
        System.out.println(value);

        Optional<String> optional1 = Optional.ofNullable(null);
        String value1 = optional1.orElse("World");
        String value2 = optional1.orElseGet(() -> "Default");
        String value3 = optional1.orElseThrow(() -> new IllegalArgumentException());
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
    }
}
