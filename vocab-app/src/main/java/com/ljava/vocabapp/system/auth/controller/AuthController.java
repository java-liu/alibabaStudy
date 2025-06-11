package com.ljava.vocabapp.system.auth.controller;

import com.ljava.vocabapp.handler.CustomAuthenticationFailureHandler;
import com.ljava.vocabapp.handler.CustomAuthenticationSuccessHandler;
import com.ljava.vocabapp.system.auth.request.LoginRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    /**
     * 处理登录请求
     * @param loginRequest 登录请求体，包含用户名和密码
     * @param request HTTP 请求对象
     * @param response HTTP 响应对象
     * @return 返回一个欢迎消息或重定向到登录页面的视图
     */
    @GetMapping("/login")
    public String hello(@RequestBody LoginRequest loginRequest,
                        HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        try {
            // 这里可以添加登录逻辑，比如使用 authenticationManager 进行认证
             Authentication authentication = authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
             //SecurityContextHolder.getContext().setAuthentication(authentication);
             new CustomAuthenticationSuccessHandler().onAuthenticationSuccess(request, response, authentication);
            return "Login successful: " + authentication.getName();
        } catch (AuthenticationException e) {
            // 认证失败，调用 failureHandler
            new CustomAuthenticationFailureHandler().onAuthenticationFailure(request, response, e);
            return "Login failed: " + e.getMessage();
        }
        // 这里可以返回一个登录页面的视图或重定向到登录页面
        //return "Hello, welcome to the Vocab App! Please log in.";
    }
}
