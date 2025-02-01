package com.example.easypickSkinCareApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/**").permitAll() // 해당 API는 인증 없이 접근 가능
                .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요
        )
        .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
        .formLogin(form -> form
                .loginPage("/login") // 로그인 페이지 지정 (옵션)
                .permitAll() // 로그인 페이지는 인증 없이 접근 가능
            );
        
        return http.build();
    }
}