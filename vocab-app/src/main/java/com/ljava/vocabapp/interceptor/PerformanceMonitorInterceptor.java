package com.ljava.vocabapp.interceptor;

import com.ljava.vocabapp.service.MetricsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 性能监控拦截器
 * 1.监控API响应时间
 * 2.识别性能瓶颈
 * 3.统计慢查询
 * 4.提供性能指标用于系统优化
 */
@Slf4j
@Component
@AllArgsConstructor
public class PerformanceMonitorInterceptor implements HandlerInterceptor {
    private final long slowRequestThreshold = 500; // 慢请求阈值，单位毫秒

    private final MetricsService metricsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //log.info("PerformanceMonitorInterceptor.preHandle() called for: {}", request.getRequestURI());
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String controllerName = handlerMethod.getBeanType().getSimpleName();
            String methodName = handlerMethod.getMethod().getName();

            request.setAttribute("controllerName", controllerName);
            request.setAttribute("methodName", methodName);
            request.setAttribute("startTime", System.currentTimeMillis());
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //log.info("PerformanceMonitorInterceptor.afterCompletion() called");
        String controllerName = (String) request.getAttribute("controllerName");
        String methodName = (String) request.getAttribute("methodName");
        String uri = request.getRequestURI();
        long startTime = (Long) request.getAttribute("startTime");
        long processingTime = System.currentTimeMillis() - startTime;

        //记录API性能指标
        metricsService.recordApiPerformance(controllerName, methodName, uri, processingTime);

        //如果是慢请求，记录慢请求
        if(processingTime > slowRequestThreshold) {
            metricsService.recordSlowRequest(controllerName, methodName, uri, processingTime);
            log.warn("Slow request detected: {}#{} took {} ms", controllerName, methodName, processingTime);

            //记录慢请求到专门的监控系统
            metricsService.recordSlowRequest(controllerName, methodName, uri, processingTime);
        }
    }
}
