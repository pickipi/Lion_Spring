package com.example.securityexam4.controller;

import com.example.securityexam4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 1. 회원가입 폼 요청
    @GetMapping("/signup")
    public String signUp(){

        return "/exam4/users/signup";
    }

    // 2. 회원가입 요청
    @PostMapping("/userreg")
    public String userReg(){

        return "redirect:/welcome";
    }

    // 3. 로그인 폼 요청
    @GetMapping("/loginform")
    public String loginForm(){

        return "exam4/users/loginform";
    }
}
