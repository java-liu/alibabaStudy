package com.ljava.vocabapp.service.impl;

import com.ljava.vocabapp.entity.ApiPerformanceMetric;
import com.ljava.vocabapp.entity.SlowRequestRecord;
import com.ljava.vocabapp.service.MetricsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Service
@Slf4j
public class MetricsServiceImpl implements MetricsService {
    //使用滑动窗口记录最近的性能数据
    private final ConcurrentMap<String, SlidingWindowMetric> apiMetrics = new ConcurrentHashMap<>();

    // 用于存储慢请求记录的队列
    private final Queue<SlowRequestRecord> slowRequestRecords = new ConcurrentLinkedDeque<>();

    private static final int MAX_SLOW_REQUESTS = 100; // 最大慢请求记录数

    @Override
    public void recordApiPerformance(String controller, String method, String uri, long processingTime) {
        String apiKey = controller + "#" + method;

        apiMetrics.computeIfAbsent(apiKey, k -> new SlidingWindowMetric())
                .addSample(processingTime);

        //可以在这里添加其它监控系统的指标记录
    }

    @Override
    public void recordSlowRequest(String controller, String method, String uri, long processingTime) {
        SlowRequestRecord requestRecord = new SlowRequestRecord(
                controller, method, uri, processingTime, LocalDateTime.now());
        slowRequestRecords.add(requestRecord);

        //如果队列超过最大容量,移除最早的记录
        if (slowRequestRecords.size() > MAX_SLOW_REQUESTS) {
            slowRequestRecords.poll();
        }
    }

    @Override
    public List<ApiPerformanceMetric> getApiPerformanceMetrics() {
        List<ApiPerformanceMetric> metricsList = new ArrayList<>();

        for(Map.Entry<String, SlidingWindowMetric> entry : apiMetrics.entrySet()) {
            String[] parts = entry.getKey().split("#");
            String controller = parts[0];
            String method = parts.length > 1 ? parts[1] : "";
            SlidingWindowMetric metric = entry.getValue();

            ApiPerformanceMetric metricData = new ApiPerformanceMetric(
                    controller,
                    method,
                    metric.getAvg(),
                    metric.getMin(),
                    metric.getMax(),
                    metric.getCount()
            );
            metricsList.add(metricData);
        }
        return metricsList;
    }

    @Override
    public List<SlowRequestRecord> getSlowRequestRecords() {
        return new ArrayList<>(slowRequestRecords);
    }
    //滑动窗口度量类
    private static class SlidingWindowMetric {
        private final LongAdder count = new LongAdder();
        private final LongAdder sum = new LongAdder();
        private final AtomicLong min = new AtomicLong(Long.MAX_VALUE);
        private final AtomicLong max = new AtomicLong(0);

        public void addSample(long value){
            count.increment();
            sum.add(value);

            //更新最小值
            while(true) {
                long currentMin = min.get();
                if(value >= currentMin || min.compareAndSet(currentMin, value)) {
                    break;
                }
            }
            //更新最大值
            while(true) {
                long currentMax = max.get();
                if(value <= currentMax || max.compareAndSet(currentMax, value)) {
                    break;
                }
            }
        }

        public long getCount() {
            return count.sum();
        }


        public long getAvg(){
            long countValue = count.sum();
            return countValue == 0 ? 0 : sum.sum() / countValue;
        }

        public long getMin() {
            return min.get();

        }
        public long getMax() {
            return max.get();
        }
        // Getters and other methods can be added here
    }
}
