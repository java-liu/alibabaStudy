package com.ljava.auth2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 响应类
 * @JsonInclude(JsonInclude.Include.NON_NULL) 注解，
 * 作用是配置 Jackson 序列化时忽略值为 null 的字段，避免这些字段出现在生成的 JSON 数据中。
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReponseResult<T> {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示消息,如果有错误时,前端可以使用此消息提示用户
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;

    public ReponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}
