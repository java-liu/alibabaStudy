package com.ljava.vocabapp.service;

import com.ljava.vocabapp.entity.ApiPerformanceMetric;
import com.ljava.vocabapp.entity.SlowRequestRecord;

import java.util.List;

public interface MetricsService {
    void recordApiPerformance(String controller, String method,String uri,long processingTime);

    void recordSlowRequest(String controller, String method, String uri, long processingTime);

    List<ApiPerformanceMetric> getApiPerformanceMetrics();

    List<SlowRequestRecord> getSlowRequestRecords();
}
