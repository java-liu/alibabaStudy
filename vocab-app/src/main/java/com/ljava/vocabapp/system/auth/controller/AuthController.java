package com.ljava.vocabapp.system.auth.controller;

import com.ljava.vocabapp.handler.CustomAuthenticationFailureHandler;
import com.ljava.vocabapp.handler.CustomAuthenticationSuccessHandler;
import com.ljava.vocabapp.provider.JwtTokenProvider;
import com.ljava.vocabapp.result.RR;
import com.ljava.vocabapp.system.auth.request.LoginRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 处理登录请求
     * @param loginRequest 登录请求体，包含用户名和密码
     * @param request HTTP 请求对象
     * @param response HTTP 响应对象
     * @return 返回一个欢迎消息或重定向到登录页面的视图
     */
    @PostMapping("/login")
    public RR<?> hello(@RequestBody LoginRequest loginRequest,
                        HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        try {
            // 这里可以添加登录逻辑，比如使用 authenticationManager 进行认证
             Authentication authentication = authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
             //SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(loginRequest.getUsername());

            //new CustomAuthenticationSuccessHandler().onAuthenticationSuccess(request, response, authentication);
            return RR.success("success", Map.of(
                            "username", loginRequest.getUsername(),
                            "roles", authentication.getAuthorities(),
                            "token", token
                    ));

        } catch (AuthenticationException e) {
            // 认证失败，调用 failureHandler
            //new CustomAuthenticationFailureHandler().onAuthenticationFailure(request, response, e);
            return handleAuthenticationFailure(e, response);
        }
        // 这里可以返回一个登录页面的视图或重定向到登录页面
        //return "Hello, welcome to the Vocab App! Please log in.";
    }

    // 自定义失败响应
    private RR<?> handleAuthenticationFailure(AuthenticationException e, HttpServletResponse response) {
        String errorMessage;
        if (e.getMessage().contains("Bad credentials")) {
            errorMessage = "用户名或密码错误";
        } else if (e instanceof DisabledException) {
            errorMessage = "账户已被禁用";
        } else if (e instanceof LockedException) {
            errorMessage = "账户已被锁定";
        } else if (e instanceof AccountExpiredException) {
            errorMessage = "账户已过期";
        } else {
            errorMessage = "登录失败: " + e.getMessage();
        }

        return RR.error(HttpStatus.UNAUTHORIZED.getReasonPhrase(),Map.of(
                "status", "error",
                "message", errorMessage
        ));
    }
}
