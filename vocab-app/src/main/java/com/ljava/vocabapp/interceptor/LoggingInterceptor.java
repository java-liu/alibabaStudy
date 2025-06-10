package com.ljava.vocabapp.interceptor;

import com.ljava.vocabapp.entity.UserEntity;
import com.ljava.vocabapp.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 日志拦截器，用于记录请求和响应的详细信息。
 * 1.记录API请求和响应内容
 * 2.记录请求开始和结束时间
 * 3.跟踪用户行为
 * 4.收集系统使用统计数据
 * 5.辅助问题排查和性能分析
 * 6.最后要把这个拦截器注册到Spring MVC的配置中，以便它能在请求处理过程中被调用。
 * 步骤
 * 1.ContentCachingFilter 缓存请求体
 * 目的:保证请求体可多次读取
 * 2,LoggingInterceptor.preHandle() 读取缓存内容并记录日志
 * 目的:记录完整的请求信息
 * 3.请求处理完成后调用 afterCompletion()
 * 目的:记录响应状态和耗时
 * 4.ContentCachingFilter.copyBodyToResponse() 写回响应
 * 目的:确保客户端收到正确响应
 * 建议优化方向:
 * 如果日志量过大，可以对敏感字段（如密码）脱敏处理；
 * 可以将日志写入数据库或日志平台进行集中管理；
 * 对大文件上传/下载场景考虑是否跳过缓存，避免内存占用过高。
 */
@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //记录请求开始时间
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        //记录请求的基本信息
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        //获取当前用户 (用户已通过认证拦截器)
        Object currentUser = request.getAttribute("currentUser");
        String username = (currentUser != null) ? ((UserEntity) currentUser).getUsername() : "未登录用户";

        //记录请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder params = new StringBuilder();
        if(parameterMap != null && !parameterMap.isEmpty()) {
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                params.append(entry.getKey()).append("=").append(String.join(",", entry.getValue())).append("&");
            }
            // 去掉最后的 '&'
            if (params.length() > 0) {
                params.setLength(params.length() - 1);
            }
        } else {
            params.append("无请求参数");
        }

        //记录请求体 (仅POST/PUT/PATCH请求)
        String requestBody = "";
        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "PATCH".equalsIgnoreCase(method)) {
            //如果没有 ContentCachingFilter，这段代码在后续组件已经读取过流后会抛出异常或返回空内容。
            requestBody = RequestUtils.getRequestBody(request);
            //requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        }

        // 记录请求信息
        log.info( "请求信息: 用户: {}, 请求URI: {}, 方法: {}, IP: {}, User-Agent: {}, 参数: {}, 请求体: {}",
                username, requestURI, method, remoteAddr, userAgent, params.toString(), requestBody);
        return true; // 继续处理请求
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //记录请求结束时间
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        //记录响应状态码
        int status = response.getStatus();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        if(ex != null){
            // 如果有异常，记录异常信息
            log.error("请求处理异常: 请求URI: {}, 方法: {}, 状态码: {}, 异常信息: {}", requestURI, method, status, ex.getMessage());
        } else {
            // 正常完成请求
            log.info("请求处理完成: 请求URI: {}, 方法: {}, 状态码: {}, 耗时: {}ms", requestURI, method, status, duration);
        }
    }
}
