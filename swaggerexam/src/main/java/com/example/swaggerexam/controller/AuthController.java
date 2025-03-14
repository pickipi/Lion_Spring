package com.example.swaggerexam.controller;

import com.example.swaggerexam.dto.LoginRequestDto;
import com.example.swaggerexam.dto.RegisterRequestDto;
import com.example.swaggerexam.service.UserService;
import com.example.swaggerexam.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

// 로그인/회원가입/로그아웃/User관리
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "인증 관련 API")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Operation(
            summary = "회원가입",
            description = "이메일과 비밀번호를 입력하여 회원가입을 합니다.",
            tags = {"Authentication"}
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto requestDto){
        try {
            userService.register(requestDto.getEmail(), requestDto.getPassword());
            return ResponseEntity.ok("회원가입 성공!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "로그인", description = "사용자가 모임 관리 서비스에 로그인합니다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto){
        String token = userService.login(requestDto.getEmail(), requestDto.getPassword());
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @Operation(summary = "로그아웃", description = "사용자가 모임 관리 서비스로부터 로그아웃합니다.")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @Parameter(description = "JWT 인증 토큰", required = true, example = "Bearer eyJhbGciOiJIUzI....")
            @RequestHeader("Authorization") String token
    ){
        if(token.startsWith("Bearer ")){
            token = token.substring(7);
        }
        jwtUtil.invalidateToken(token);
        return ResponseEntity.ok("로그아웃 성공");
    }

    // Todo: 사용자 ID를 이용한 정보 조회 - 미구현
//    @Operation(summary = "사용자 정보 조회", description = "사용자의 고유 ID를 이용하여 정보를 조회합니다.")
//    @GetMapping("/users/{id}")
//    public ResponseEntity<UserDto> getUserById(
//            @PathVariable("id") Long id) {
////        UserDto user = userService.getUserById(id);
//        return ResponseEntity.ok(null);
//    }
}