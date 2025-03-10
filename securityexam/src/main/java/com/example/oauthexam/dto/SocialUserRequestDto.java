package com.example.oauthexam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialUserRequestDto {
    @NotBlank(message = "Provider는 필수값입니다.")
    private String provider;
    private String socialId;
    private String uuid;
    private String name;
    private String email;
    private String username;
}
