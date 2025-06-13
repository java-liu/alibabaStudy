package com.ljava.vocabapp.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    ;
    final int code; // 响应状态码
    final String message; // 响应消息
    final String errorCode = "0"; // 错误码，默认为0
}