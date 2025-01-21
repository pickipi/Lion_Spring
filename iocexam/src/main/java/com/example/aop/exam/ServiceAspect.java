package com.example.aop.exam;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// Pointcut + Advice
@Aspect
@Component
public class ServiceAspect {
    @Pointcut("execution(* com.example.aop.exam.*Service.*(..))")
    public void pc(){

    }

    // "execution(리턴타입 패키지경로.클래스이름.메소드이름(매개변수))
    // exam패키지만 포함, SimpleService클래스의 모든메소드(*)를 사용하며 매개변수도 상관없음(..)
    @Before("execution(* com.example.aop.exam.*Service.*(..))")
//    @Before("execution(* com.example..)"); // example패키지 안의 다른 패키지들도 포함
    public void before(JoinPoint joinPoint){// before 매개변수로는 Joinpoint를 받아낼 수 있음
        System.out.println("Before의 메소드(before()) 실행 :::::::::::::: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.aop.exam.*Service.*(..))")
    public void after(){
        System.out.println("After의 메소드 (after()) 실행 :::::::::::::: ");
    }

    
}
