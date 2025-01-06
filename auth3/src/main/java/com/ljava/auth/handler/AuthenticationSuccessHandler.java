package com.ljava.auth.handler;

import com.ljava.auth.module.user.entity.User;
import com.ljava.auth.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
        // 获取用户信息
        User user = (User) authentication.getPrincipal();
        // 生成token
        String token = JwtUtil.generateToken(user.getUsername());
        // 将token放入响应头
        response.setHeader("Authorization", token);
        // 跳转到首页
        response.sendRedirect("/");
    }
}
