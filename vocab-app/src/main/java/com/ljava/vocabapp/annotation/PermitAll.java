package com.ljava.vocabapp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记方法或类可以被所有用户访问，无需身份验证。
 */
@Target(ElementType.METHOD)  // 表示该注解可以应用于方法上
@Retention(RetentionPolicy.RUNTIME) // 表示该注解在运行时依然可用,便于程序通过反射读取
public @interface PermitAll {
}
