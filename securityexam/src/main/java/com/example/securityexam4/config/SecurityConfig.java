package com.example.securityexam4.config;

import com.example.securityexam4.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/myinfo","/signup","/userreg", "/userreg_role","/loginform","/").permitAll()
                        .requestMatchers("/welcome","/shop/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
                // 기본 로그인 폼이 아닌 사용자 정의 로그인 폼으로 변경
//                .formLogin(Customizer.withDefaults());
        http
                .formLogin(form ->form
                        .loginPage("/loginform")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/welcome")
                )
                .logout(logout ->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .sessionManagement(session -> session
                        .maximumSessions(1) // 동시 접속 가능한 세션 수 설정 - ex. 3이면 3개 이상 세션부터는 로그인 유지를 하지 않음
                        .maxSessionsPreventsLogin(false) // 첫번째 로그인된 사용자가 차단됨

                )
                .userDetailsService(customUserDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
