package com.example.securityexam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // 1. 기본설정
//        http
//                .authorizeHttpRequests( auth -> auth
//                    .anyRequest().authenticated()
//                    // 모든 요청에 대해서 인증을 요구
//                )
//                .formLogin(Customizer.withDefaults());

        // 2. 인증없이 접근 가능한 URL 지정 및 (.requestMatchers())
        // 로그인 페이지와 로그인 성공 시 이동할 페이지 설정 (.formLogin())
        http
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/hello","/loginForm").permitAll()
                        .anyRequest().authenticated()
                        // 모든 요청에 대해서 인증을 요구
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/loginForm") // 원하는 로그인 페이지 설정
                        .defaultSuccessUrl("/success") // 인증에 성공하면 가고싶은 페이지 설정
                        .failureUrl("/fail") // 인증에 실패하면 가고싶은 페이지 설정
                        .usernameParameter("userId") // 로그인 폼에서의 Input 상자의 ID부분과 일치해야함
                        .passwordParameter("password") // 로그인 폼에서의 Input 상자의 PASSWORD부분과 일치해야함
                );
        return http.build();
    }
}
