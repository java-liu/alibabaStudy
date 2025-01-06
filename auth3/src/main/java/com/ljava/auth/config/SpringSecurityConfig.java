package com.ljava.auth.config;

import com.ljava.auth.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 1. passwordEncoder() 方法
 * 功能：返回一个 BCryptPasswordEncoder 实例，用于密码的加密和验证。
 * 使用场景：
 * 在用户注册时，使用 passwordEncoder().encode(rawPassword) 方法对用户的原始密码进行加密。
 * 在用户登录时，使用 passwordEncoder().matches(rawPassword, encodedPassword) 方法验证用户输入的密码是否与数据库中存储的加密密码匹配。
 * 2. authenticationManager() 方法
 * 功能：返回一个 AuthenticationManager 实例，用于处理身份验证请求。
 * 使用场景：
 * 在自定义的身份验证过滤器或其他安全配置中，注入 AuthenticationManager 并使用它来处理用户的认证请求。
 * 定义这两个 Bean 的原因
 * passwordEncoder()：
 * 安全性：使用 BCryptPasswordEncoder 可以确保密码的安全性，因为它是一种强哈希算法，能够有效防止密码被破解。
 * 统一管理：通过定义为 Bean，可以在整个应用中统一管理和使用同一个密码编码器，避免重复代码和不一致的问题。
 * authenticationManager()：
 * 依赖注入：将 AuthenticationManager 定义为 Bean，可以在需要的地方通过依赖注入的方式获取和使用，提高代码的可维护性和灵活性。
 * 配置分离：将身份验证管理的配置单独定义，使得安全配置更加清晰和模块化。
 */
@Configuration
public class SpringSecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       //旧的方式配置
         /*http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                 .and()
                 .addFilter();*/
        //return http.build();

        /**
         * 具体提升
         * 可读性：
         * 旧的方式中，多个方法调用链在一起，可能会导致代码难以阅读和理解。
         * 新的方式使用Lambda表达式，使每个配置步骤更加清晰。
         * 类型安全：
         * 旧的方式中，方法调用链较长时，容易出现类型错误。
         * 新的方式中，Lambda表达式提供了更好的类型检查，减少了类型错误的可能性。
         * 灵活性：
         * 旧的方式中，复杂的配置逻辑需要额外的方法或类来实现。
         * 新的方式中，可以在Lambda表达式内部直接编写复杂的逻辑，更加灵活。
         * 一致性：
         * 新的方式与Spring Security的其他部分保持一致，使用现代Java开发的最佳实践。
         * 通过这些改进，代码不仅更加现代化，而且更易于维护和扩展。
         */
        /**
         * 禁用CSRF保护：
         * http.csrf().disable()
         * 关闭跨站请求伪造保护，适用于不需要CSRF保护的场景。
         * 配置HTTP请求授权：
         * http.authorizeHttpRequests()
         * 开始配置HTTP请求的授权规则。
         * 允许所有用户访问特定路径：
         * requestMatchers("/auth/**").permitAll()
         * 允许所有用户访问路径为/auth/**的请求，无需身份验证。
         * 其他请求需要身份验证：
         * anyRequest().authenticated()
         * 所有其他请求都需要经过身份验证。
         * 添加自定义过滤器：
         * addFilter()
         * 添加自定义的过滤器，用于处理特定的安全需求
         */
        //新的方式配置
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(formLogin -> formLogin
                        .loginPage("/auth/hello")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                ).logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );
               // .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加一个自定义过滤器
        //.addFilterBefore(new FilterUsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * 功能：返回一个 AuthenticationManager 实例，用于处理身份验证请求。
     *  * 使用场景：
     *  * 在自定义的身份验证过滤器或其他安全配置中，注入 AuthenticationManager 并使用它来处理用户的认证请求。
     * @param userDetailsService
     * @param passwordEncoder
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("ljava")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("USER")
                .build());
        return manager;
    }

    /**
     * 功能：返回一个 BCryptPasswordEncoder 实例，用于密码的加密和验证。
     *  * 使用场景：
     *  * 在用户注册时，使用 passwordEncoder().encode(rawPassword) 方法对用户的原始密码进行加密。
     *  * 在用户登录时，使用 passwordEncoder().matches(rawPassword, encodedPassword) 方法验证用户输入的密码是否与数据库中存储的加密密码匹配。
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
