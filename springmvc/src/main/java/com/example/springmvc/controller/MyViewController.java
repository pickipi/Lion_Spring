package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyViewController {
    @GetMapping("/custom")
    public String customView(){
        return "my-prefix-custom"; // my-prefix로 시작하도록 설정해주었으므로 연동
        // 만약 config패키지의 MyCustomViewResolver, WebConfig클래스 등에서 설정하지 않았다면
        // templates 패키지에서 따로 custom.html을 찾아서 실행하고자 했을 것이다.
    }
}
