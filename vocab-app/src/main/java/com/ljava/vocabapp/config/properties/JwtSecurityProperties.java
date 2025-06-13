package com.ljava.vocabapp.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "vocabapp.security.jwt")
public class JwtSecurityProperties {


    private boolean enabled;

    private List<String> skipUrls;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getSkipUrls() {
        return skipUrls;
    }

    public void setSkipUrls(List<String> skipUrls) {
        this.skipUrls = skipUrls;
    }
}
