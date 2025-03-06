package com.example.jwtexam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class UserRegisterDTO {
    // 필드들은 signup의 (회원가입 폼의) 속성이름들과 일치해야함
    private String username;
    private String password;
    private String name;
    private String email;

    // 권한 선택의 roles 같은 경우 USER, ADMIN 둘다 roles인데,
    // roles 리스트 안에 USER, ADMIN이 들어가는 형태이다.
    private List<String> roles;
}
