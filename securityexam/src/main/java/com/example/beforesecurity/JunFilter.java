package com.example.beforesecurity;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

// Bean으로 등록하기위해 @Component 사용
@Slf4j
//@Component // FilterConfig에서 수동으로 Bean등록을 해주었기때문에 주석처리
//@WebFilter(urlPatterns = "/test/*")
public class JunFilter implements Filter {
    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        log.info("JunFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("JunFilter doFilter() 실행 전" + Thread.currentThread().getName());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("JunFilter doFilter() 실행 후" + Thread.currentThread().getName());
    }

    @Override
    public void destroy() {
        log.info("JunFilter destroy()");
    }
}
