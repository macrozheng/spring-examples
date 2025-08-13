package com.macro.blog.springdoc.config;

import com.macro.blog.springdoc.component.JwtAuthenticationTokenFilter;
import com.macro.blog.springdoc.component.RestAuthenticationEntryPoint;
import com.macro.blog.springdoc.component.RestfulAccessDeniedHandler;
import com.macro.blog.springdoc.domain.AdminUserDetails;
import com.macro.blog.springdoc.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @auther macrozheng
 * @description SpringSecurity的配置
 * @date 2025/8/12
 * @github https://github.com/macrozheng
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Lazy
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(registry -> {
            //不需要保护的资源路径允许访问
            for (String url : ignoreUrlsConfig.getUrls()) {
                registry.requestMatchers(url).permitAll();
            }
            //允许跨域请求的OPTIONS请求
            registry.requestMatchers(HttpMethod.OPTIONS)
                    .permitAll();
        })
        //任何请求需要身份认证
        .authorizeHttpRequests(registry -> registry.anyRequest().authenticated())
        //关闭跨站请求防护
        .csrf(AbstractHttpConfigurer::disable)
        //修改Session生成策略为无状态会话
        .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //自定义权限拒绝处理类
        .exceptionHandling(configurer -> configurer.accessDeniedHandler(restfulAccessDeniedHandler).authenticationEntryPoint(restAuthenticationEntryPoint))
        //自定义权限拦截器JWT过滤器
        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            AdminUserDetails admin = adminService.getAdminByUsername(username);
            if (admin != null) {
                return admin;
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

}
