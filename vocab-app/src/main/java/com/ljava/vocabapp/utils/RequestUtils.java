package com.ljava.vocabapp.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import java.nio.charset.StandardCharsets;

public class RequestUtils {
    public static String getRequestBody(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper == null) return "";

        byte[] content = wrapper.getContentAsByteArray();
        return new String(content, StandardCharsets.UTF_8);
    }
}
