package com.ljava.vocabapp.interceptor;

import com.ljava.vocabapp.annotation.PermitAll;
import com.ljava.vocabapp.annotation.RequireRole;
import com.ljava.vocabapp.entity.UserEntity;
import com.ljava.vocabapp.provider.JwtTokenProvider;
import com.ljava.vocabapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器是Spring MVC框架提供的一种机制，用于在控制器(Controller)处理请求前后执行特定的逻辑。
 * 用户认证拦截器主要用于:
 * 1.验证用户是否已登录
 * 2.检查用户是否具有访问特定资源的权限
 * 3.实现无状态API的JWT token验证
 * 4.最后要把这个拦截器注册到Spring MVC的配置中，以便它能在请求处理过程中被调用。
 */
@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if(!(handler instanceof HandlerMethod)){
            // 如果不是控制器方法，则直接放行
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //检查是否有 @PermitAll注解,有则跳过认证
        PermitAll permitAll = handlerMethod.getMethodAnnotation(PermitAll.class);

        if(permitAll != null){
            // 如果方法上有 @PermitAll 注解，则跳过认证
            return true;
        }

        //从请求头中获取token
        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")){
            // 如果没有token或格式不正确，则返回401未授权
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\" : \"未授权,请先登录\"}");
            log.error("未授权访问: 请求URI: {}, 请求IP: {}, 请求参数: {}",
                      request.getRequestURI(),
                      request.getRemoteAddr(),
                      request.getParameterMap());
            return false;
        }

        token = token.substring(7); // 去掉 "Bearer " 前缀

        //验证token
        try {
            // 这里可以调用JWT工具类验证token的有效性
             if(!jwtTokenProvider.validateToken(token)){
                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                 response.getWriter().write("{\"error\" : \"无效的token\"}");
                 return false;
             }
             //从token 中获取用户信息并设置到请求属性中
            String username = jwtTokenProvider.getUsernameFromToken(token);
            UserEntity user = userService.loadUserByUsername(username);

            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\" : \"用户不存在\"}");
                log.error("用户不存在: 请求URI: {}, 请求IP: {}, 用户名: {}",
                          request.getRequestURI(),
                          request.getRemoteAddr(),
                          username);
                return false;
            }
            //检查方法是否有 @RequireRole 注解
            RequireRole requireRole = handlerMethod.getMethodAnnotation(RequireRole.class);
            if(requireRole != null){
                String[] roles = requireRole.value();
                boolean hasRole = false;
                // 检查用户是否具有所需角色
                for (String role : roles) {
                    if (user.getRoles() != null && user.getRoles().contains(role)) {
                        hasRole = true;
                        break;
                    }
                }
                if(!hasRole){
                    // 如果用户没有所需角色，则返回403禁止访问
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("{\"error\" : \"权限不足\"}");
                    log.error("权限不足: 请求URI: {}, 请求IP: {}, 用户名: {}, 所需角色: {}",
                              request.getRequestURI(),
                              request.getRemoteAddr(),
                              username,
                              String.join(", ", roles));
                    return false;
                }
                //将用户信息设置到请求属性中,供后续处理使用
                request.setAttribute("user", user);
            }
            // 如果验证通过，则继续处理请求
            return true;
        } catch (Exception e) {
            // 如果验证失败，则返回401未授权
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\" : \"无效的token\"}");
            log.error("无效的token: 请求URI: {}, 请求IP: {}, 错误信息: {}",
                      request.getRequestURI(),
                      request.getRemoteAddr(),
                      e.getMessage());
            return false;
        }
    }
}
