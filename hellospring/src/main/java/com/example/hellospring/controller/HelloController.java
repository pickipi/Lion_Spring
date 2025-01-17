package com.example.hellospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hi(){
        return "Hello Spring!";
    }

    @GetMapping("/")
    public String index(){
        return "Init 페이지 보여주기";
    }

    @GetMapping("/profile")
    public String profile(){
        return "프로필 페이지 !";
    }

    @GetMapping("/devtools")
    public String devtools(){
        return "devtools 테스트 페이지";
    }

    @GetMapping("/rerun")
    public String rerun(){
        return "devtools 테스트 페이지 2";
    }

    @GetMapping("/newPage")
    public String newPage(){
        return "devtools 테스트 페이지 3";
    }
}
