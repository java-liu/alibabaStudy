package com.ljava.vocabapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //@Autowired
    //private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // 禁用CSRF保护
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/login").permitAll() // 允许所有人访问登录页面
                    .requestMatchers("/admin/**").hasRole("ADMIN") // 仅管理员可以访问/admin路径)
                    .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // 用户和管理员可以访问/user路径
                    .anyRequest().authenticated() // 其他请求需要认证
            )
                .httpBasic(HttpBasicConfigurer::disable)
                .formLogin(FormLoginConfigurer::disable)
//                .formLogin(formLogin -> formLogin
//                .loginPage("/auth/login")
//                .defaultSuccessUrl("/home", true)
//                .failureUrl("/login?error=true")
//                .permitAll()
//        )
            .logout(LogoutConfigurer::permitAll // 启用注销功能 允许所有人访问注销功能
            );

        return http.build();
    }

    /**
     * 如果你需要自定义登录逻辑或集成 JWT 就需要它
     * 作用：
     * 认证的核心接口，用于处理用户登录认证请求。
     * 在 Spring Security 中，所有认证流程（如表单登录、JWT 登录）最终都会调用 AuthenticationManager.authenticate(...) 方法进行身份验证。
     * 🧠 工作原理简述：
     * 用户提交用户名和密码；
     * 被封装为一个 Authentication 对象（如 UsernamePasswordAuthenticationToken）；
     * 交给 AuthenticationManager 进行认证；
     * AuthenticationManager 会委托给具体的 AuthenticationProvider 去验证；
     * 成功后返回一个已认证的 Authentication 对象（包含用户信息和权限）；
     * 📌 使用场景举例：
     * 自定义登录接口（如 /login）
     * JWT 认证过滤器中使用它来验证用户名/密码
     * 集成第三方认证系统
     * @param authConfig
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    /**
     * 如果你使用数据库存储密码就必须配置密码编码器
     *  作用：
     * 密码加密与验证工具
     * 用于安全地存储用户密码（数据库中不存明文密码）
     * 比较用户输入的密码是否与加密后的密码匹配
     * 常用实现：
     * BCryptPasswordEncoder:推荐，默认使用 bcrypt 算法，加盐且安全
     * NoOpPasswordEncoder:明文对比，不推荐，仅测试用
     * Pbkdf2PasswordEncoder:使用 PBKDF2 算法
     * SCryptPasswordEncoder:更慢更安全的算法
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
       /* return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return "";
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return false;
            }
        }*/
        //默认使用 bcrypt 算法，加盐且安全
        return new BCryptPasswordEncoder();
    }
}
