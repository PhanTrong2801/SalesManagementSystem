package com.example.SalesManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // tắt bảo vệ CSRF cho REST API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // cho phép tất cả API public
                        .anyRequest().authenticated()           // yêu cầu đăng nhập cho route khác
                )
                .httpBasic(httpBasic -> {}); // có thể bật Basic Auth nếu cần
        return http.build();
    }
}