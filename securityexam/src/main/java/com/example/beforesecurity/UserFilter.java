package com.example.beforesecurity;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("userFilter doFilter 실행 전" + Thread.currentThread().getName());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("userFilter doFilter 실행 후" + Thread.currentThread().getName());
    }
}
