package com.ljava.vocabapp.filter;

import com.ljava.vocabapp.config.properties.JwtSecurityProperties;
import com.ljava.vocabapp.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * 虽然用户携带了 Token，但 Spring Security 并没有识别这个 Token 的内容，也没有把用户认证信息注入到上下文中。
 * 解决办法就是：
 * ✅ 实现 JWT 认证过滤器；
 * ✅ 解析 Token 并设置 Authentication；
 * ✅ 确保 Token 中包含用户权限；
 * ✅ 不要随意放行 /words/list，保持 .anyRequest().authenticated() 是合理的。
 *
 * 需要注意的是，这个过滤器需要在 Spring Security 的过滤器链中注册，以便在处理请求时能够正确地解析和验证 JWT。
 * SecurityConfig.java -> .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtSecurityProperties jwtSecurityProperties;
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, JwtSecurityProperties jwtSecurityProperties) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtSecurityProperties = jwtSecurityProperties;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!jwtSecurityProperties.isEnabled()){
            filterChain.doFilter(request, response); // 如果 JWT 安全配置未启用，直接继续过滤链
            return;
        }

        String servletPath = request.getServletPath();
        if(isSkipUrl(servletPath)){
            filterChain.doFilter(request, response); // 如果当前路径在跳过列表中，直接继续过滤链
            return;
        }
        String token = getTokenFromRequest(request);

        if(token != null && jwtTokenProvider.validateToken(token)){
            String username = jwtTokenProvider.getUsernameFromToken(token);
            List<SimpleGrantedAuthority> authorities = jwtTokenProvider.getAuthoritiesFromToken(token);

            Authentication auth = new UsernamePasswordAuthenticationToken(username,null, authorities);
            // 设置认证信息到 SecurityContext 中
            if (username != null) {
                // 在这里可以设置用户的认证信息到 SecurityContext 中
                 SecurityContextHolder.getContext().setAuthentication(auth);
                request.setAttribute("username", username); // 将用户名存储在请求属性中
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing JWT token");
            return; // 如果令牌无效或缺失，直接返回错误响应
        }
        filterChain.doFilter(request, response); // 继续过滤链
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 判断当前路径是否在跳过列表中
    private boolean isSkipUrl(String path) {
        if (jwtSecurityProperties.getSkipUrls() == null || jwtSecurityProperties.getSkipUrls().isEmpty()) {
            return false;
        }
        return jwtSecurityProperties.getSkipUrls().stream().anyMatch(path::startsWith);
    }
}
