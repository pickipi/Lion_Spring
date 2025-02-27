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
        try{
            log.info("userFilter doFilter 실행 전" + Thread.currentThread().getName());

            // 1. ThreadLocal에 저장하고싶은 객체가 존재할때
//            UserContext.setUser(new User("Kaoru"));

            // 2. 만약 URL로 값을 입력받고싶다면
            String name = servletRequest.getParameter("name");
            UserContext.setUser(new User(name));

            filterChain.doFilter(servletRequest, servletResponse);

            log.info("userFilter doFilter 실행 후" + Thread.currentThread().getName());
        }finally{
           UserContext.clear();
        }
    }
}
