package com.maizi.webLog;

import java.lang.annotation.*;
/**
 * @Description:    @Retention:什么时候使用注解，（RUNTIME:运行时）
 * 2：@Target:注解用于什么地方，METHOD:方法上
 * 3：@Documented 注解是否包含在JavaDoc中
 * @Author:         Liuys
 * @CreateDate:     2019/7/17 11:12
 * @Version:        1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /***
     * 日志描述信息
     * @return ""
     */
    String description() default "";
}
