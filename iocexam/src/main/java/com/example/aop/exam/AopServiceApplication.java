package com.example.aop.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AopServiceApplication.class);
    }

    // @Autowired를 통해 SimpleService를 주입시킴 (Injection)
    @Autowired
    private SimpleService simpleService;


    // CommandLineRunner의 메소드를 오버라이딩
    @Override
    public void run(String... args) throws Exception {
        System.out.println("-----simpleService Test-----");
        System.out.println(simpleService.drinking());
        simpleService.hello();
        simpleService.setName("Miguel");
        simpleService.getName();
    }
}
