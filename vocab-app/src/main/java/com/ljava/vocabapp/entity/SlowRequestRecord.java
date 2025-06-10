package com.ljava.vocabapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SlowRequestRecord {
    private String controllerName; // 控制器名称
    private String methodName; // 方法名称
    private String uri; // 请求的URI
    private long processingTime; // 处理时间
    private LocalDateTime timestamp; // 请求时间戳
}
