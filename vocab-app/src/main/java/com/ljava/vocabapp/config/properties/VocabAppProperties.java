package com.ljava.vocabapp.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "vocabapp")
public class VocabAppProperties {

    /**
     * allowCredentials(true) 表示允许携带 Cookie、Authorization Header 等认证信息。
     * addAllowedOrigin("*") 表示允许任意源访问。
     * 浏览器出于安全考虑，不允许在启用凭据的情况下使用通配符 "*" 作为源，否则会抛出异常或拒绝请求
     *
     * 需要指定允许的源
     */
    private List<String> corsAllowedOrigin;

    public List<String> getCorsAllowedOrigin() {
        return corsAllowedOrigin;
    }

    public void setCorsAllowedOrigin(List<String> corsAllowedOrigin) {
        this.corsAllowedOrigin = corsAllowedOrigin;
    }
}
