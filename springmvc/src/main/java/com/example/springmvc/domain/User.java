package com.example.springmvc.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {
    @NotNull(message = "Name을 입력해주세요. NULL일 수 없습니다.")
    private String name;

    @Email(message = "유효하지 않은 이메일 형식입니다.") // 이메일 필드가 유효한 이메일 형식을 준수해야한다는 조건을 정의
    private String email;
}
