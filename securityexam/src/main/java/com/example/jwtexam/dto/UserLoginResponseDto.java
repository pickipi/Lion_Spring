package com.example.jwtexam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 응답할때 어떠한 것을 가져갈지를 DTO로 정의
@Getter // 값을 가져와서 사용할 목적이므로 Getter사용
@Builder // 처음 생성한 값으로 바뀔 수 없음을 @Setter 대신 @Builder 사용
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private Long userId;
    private String name;
}
