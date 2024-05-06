package com.ljava.somemethod.optionalMethod;

import java.util.Optional;

/**
 *--------------------------
 * 检查Optional是否为空
 * Optional.of(T value) 方法的存在意义在于强制要求传入的值不能为 null，并且在传入 null 时会立即抛出 NullPointerException 异常。这样的设计可以避免在后续代码中因为传入了 null 值而导致的意外错误或者异常。
 *
 * 使用 Optional.of(T value) 方法时，开发者明确地表达了对值的非空要求，即传入的值不能为空。这种做法有助于编写更加健壮、稳定的代码，可以避免在代码执行过程中因为空值而导致的错误。
 *
 * 另外，Optional.of(T value) 方法返回的 Optional 对象表示一个非空的值，这意味着后续代码可以放心地使用该 Optional 对象，而不用担心空指针异常。因为如果传入 null，方法会立即抛出异常，而不是返回一个包含 null 值的 Optional 对象。
 *--------------------------
 * @author Liuys (开发人员)
 * @date 2024/4/29 (开发时间)
 * @version v1.0 (版本号)
 *-------------------------
*/
public class IsPresent {
    public static void main(String[] args) {
        // 创建一个Optional对象
        //如果 value 非null,则创建一个包含该值的Optional对象;否则,创建一个空的Optional对象
        /**
         * 空字符串（""）：
         * 空字符串是一个长度为 0 的字符串，即字符串中不包含任何字符。
         * 可以通过 String.isEmpty() 方法或者判断字符串长度是否为 0 来检查字符串是否为空字符串。
         * 空字符串不同于 null，它表示一个有效的、存在的字符串对象。
         * null：
         * null 表示一个引用类型的变量没有指向任何对象，即该变量没有引用任何有效的对象。
         * 在 Java 中，null 是一个特殊的关键字，表示一个空值或者不存在的对象。
         * null 可以用于任何引用类型的变量，包括字符串、对象、数组等
         */
        Optional<String> optional = Optional.of("");
        // 判断Optional是否为空,检查Optional对象是否包含值
        if (optional.isPresent()) {
            // 如果 Optional 对象包含值,可以使用get()方法获取该值:
            System.out.println(optional.get());
        }else{
            optional.get();
            System.out.println("Optional is empty");
        }
    }
}
