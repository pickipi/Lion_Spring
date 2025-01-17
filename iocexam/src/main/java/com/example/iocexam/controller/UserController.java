package com.example.iocexam.controller;

import com.example.iocexam.domain.User;
import com.example.iocexam.service.UserService;

public class UserController {
    // 유저컨트롤러를 요청하면 UserService에 의존하고 있을 것
    private UserService userService;

    public void joinUser(){
        // 실제로 동작할때는 User정보를 사용자한테 받아올 것 (사용자가 정보를 주면서 회원가입해달라고 요청할 것)
        User user = new User();
        user.setName("jones");
        user.setEmail("premier@league.com");
        user.setPassword("1111");

        userService.joinUser(user);
    }
}
