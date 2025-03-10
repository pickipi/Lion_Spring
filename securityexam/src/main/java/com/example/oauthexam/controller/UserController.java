package com.example.oauthexam.controller;

import com.example.oauthexam.service.SocialLoginInfoService;
import com.example.oauthexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SocialLoginInfoService socialLoginInfoService;

    // 로그인 폼
    @GetMapping("/loginform")
    public String loginform(){
        return "";
    }
}
