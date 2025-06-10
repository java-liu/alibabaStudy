package com.ljava.vocabapp.controller;

import com.ljava.vocabapp.entity.ApiPerformanceMetric;
import com.ljava.vocabapp.entity.SlowRequestRecord;
import com.ljava.vocabapp.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/metrics")
public class MetricsController {

    @Autowired
    private MetricsService metricsService;

    @GetMapping("/api-performance")
    public List<ApiPerformanceMetric> getApiPerformanceMetrics() {
        return metricsService.getApiPerformanceMetrics();
    }

    @GetMapping("/slow-requests")
    public List<SlowRequestRecord> getSlowRequestRecords() {
        return metricsService.getSlowRequestRecords();
    }
}
