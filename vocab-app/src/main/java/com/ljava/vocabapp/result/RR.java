package com.ljava.vocabapp.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * 通用响应结果类 ResponseResult
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RR<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code; // 响应状态码
    private String message;  // 响应消息

    private T data;     // 响应数据

    private Map<String, T> dataMap; // 响应数据映射

    public static <T> RR<T> success(String message, T data) {
        return new RR<>(ResultCode.SUCCESS.getCode(), message, data, null);
    }

    public static <T> RR<T> success(String message, Map<String, T> dataMap) {
        return new RR<>(ResultCode.SUCCESS.getCode(), message, null, dataMap);
    }

    public static <T> RR<T> error(String message) {
        return new RR<>(ResultCode.ERROR.getCode(), message, null, null);
    }

    public static <T> RR<T> error(String message, T data) {
        return new RR<>(ResultCode.ERROR.getCode(), message, data, null);
    }
    public static <T> RR<T> error(String message, Map<String, T> dataMap) {
        return new RR<>(ResultCode.ERROR.getCode(), message, null, dataMap);
    }


}
