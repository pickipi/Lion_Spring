package com.example.swaggerexam.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Operation(
            summary = "회원가입",
            description = "이메일과 비밀번호를 입력하여 회원가입을 합니다."
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ){
        return ResponseEntity.ok("ok");
    }
}
