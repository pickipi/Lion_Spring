package com.example.aop.exam;

import org.springframework.stereotype.Component;

@Component
public class SimpleService {
    // 핵심관심. = 개발자가 구현할 것

    public String drinking(){
        //target
        System.out.println("SimpleService (drinking()) 실행");
        return "물을 마십니다.";
    }

    public void hello(){
        System.out.println("SimpleService (hello()) 실행");
    }

    public void setName(String name){
        System.out.println("setName() 실행");
    }

    public void getName(){
        System.out.println("getName() 실행");
    }
}
