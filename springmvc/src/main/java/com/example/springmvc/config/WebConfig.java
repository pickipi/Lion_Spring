package com.example.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {
    // 사용자 정의 뷰가 사용될 수 있도록 Bean으로 등록
    @Bean
    public MyCustomViewResolver myCustomViewResolver(){
        MyCustomViewResolver resolver = new MyCustomViewResolver();
        resolver.setOrder(0); // 가장 먼저 실행되도록 0의 옵션값을 지정
        return resolver;
    }
}
