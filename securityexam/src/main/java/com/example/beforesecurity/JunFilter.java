package com.example.beforesecurity;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Bean으로 등록하기위해 @Component 사용
@Slf4j
@Component
//@WebFilter(urlPatterns = "/test/*")
public class JunFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("JunFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("JunFilter doFilter() 실행 전");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("JunFilter doFilter() 실행 후");
    }

    @Override
    public void destroy() {
        log.info("JunFilter destroy()");
    }
}
