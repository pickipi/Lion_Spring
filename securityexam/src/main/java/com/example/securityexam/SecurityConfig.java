package com.example.securityexam;

import jakarta.servlet.http.HttpSession;
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
                        .requestMatchers("/hello","/loginForm", "fail", "/test/*").permitAll()
                        .anyRequest().authenticated()
                        // 모든 요청에 대해서 인증을 요구
                )
                .formLogin(formLogin -> formLogin
                        .loginProcessingUrl("/login_proc") // 기본 login
//                        .loginPage("/login") // 원하는 로그인 페이지 설정
//                        .defaultSuccessUrl("/success") // 인증에 성공하면 가고싶은 페이지 설정
//                        .failureUrl("/fail") // 인증에 실패하면 가고싶은 페이지 설정
                        .usernameParameter("userId") // 로그인 폼에서의 Input 상자의 ID부분과 일치해야함
                        .passwordParameter("password") // 로그인 폼에서의 Input 상자의 PASSWORD부분과 일치해야함

                        .successHandler((request, response, authentication) -> {
                            log.info("로그인 성공!" + authentication.getName());
                            response.sendRedirect("/info"); // 로그인 성공 시 이동할 페이지 설정
                        })

                        .failureHandler(((request, response, exception) -> {
                            log.info("로그인 실패!" + exception.getMessage());
                            response.sendRedirect("/login");
                        }))
                );

        // 3. 로그아웃 기능 추가
        http
                .logout(logout-> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/hello") // 1) 단순히 로그아웃 시 이동할 페이지 설정

                        // 2) 로그아웃 시 수행할 일 지정 가능
                        .addLogoutHandler((request, response, authentication) -> {
                            log.info("로그아웃 세션, 쿠키 삭제!");
                            HttpSession session = request.getSession();
                            if(session != null){
                                session.invalidate(); // 세션 삭제
                            }
                        })
                        .deleteCookies("JSESSIONID") // 로그아웃 시에 원하는 쿠키를 삭제할 수 있음
        );
        return http.build();
    }
}
