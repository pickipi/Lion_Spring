package com.example.swaggerexam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "회원가입 요청 DTO") // Swagger의 어노테이션
public class RegisterRequestDto {
    // 데이터가 들어가는 부분에 샘플데이터를 지정해서 넣어줄 수 있음
    @Schema(description = "사용자 이메일", example = "user@example.com")
    private String email;

    @Schema(description = "사용자 비밀번호", example = "password123")
    private String password;
}
