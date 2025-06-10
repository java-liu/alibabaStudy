package com.ljava.vocabapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiPerformanceMetric {
    private String controllerName; // 控制器名称
    private String methodName; // 方法名称
    private double avgProcessingTime; // 平均处理时间
    private long minProcessingTime; // 最小处理时间
    private long maxProcessingTime; // 最大处理时间
    private long requestCount; // 请求次数
}
