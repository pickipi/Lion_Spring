package com.example.beforesecurity;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig{
    @Bean
    public FilterRegistrationBean<JunFilter> junFilter(){
        FilterRegistrationBean<JunFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JunFilter());
        registrationBean.addUrlPatterns("/test/*");
        // URL에서 /test 밑에 필터가 요청되게끔 지정할 수 있다.
        // * 대신 특정 URL을 적어주면 그 URL에 대해서만 필터가 요청되게끔 설정가능

        registrationBean.setOrder(2);
        return registrationBean;
    }
}
