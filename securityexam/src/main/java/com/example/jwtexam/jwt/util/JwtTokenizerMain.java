package com.example.jwtexam.jwt.util;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
public class JwtTokenizerMain {
    public static void main(String[] args) {
        JwtTokenizer jwtTokenizer = new JwtTokenizer(
                "12345678901234567890123456789012",
                "12345678901234567890123456789012");

        // Access Token 생성
        String accessToken = jwtTokenizer.createAccessToken(1L, "test@test.com", "test", "testuser", Arrays.asList("ROLE_USER"));
        log.info("[ACCESS TOKEN] : {}", accessToken);

        // Refresh Token 생성
        String refreshToken = jwtTokenizer.createRefreshToken(1L, "test@test.com", "test", "testuser", Arrays.asList("ROLE_USER"));
        log.info("[REFRESH TOKEN] : {}", refreshToken);

        // parseToken 테스트
        byte[] secretKey = "12345678901234567890123456789012".getBytes(StandardCharsets.UTF_8);

        Claims claims = jwtTokenizer.parseToken(accessToken, secretKey);
        log.info(claims.getSubject());
        log.info((String) claims.get("username"));

        // Bearer 테스트
        accessToken = "Bearer " + accessToken;
        Long userIdFromToken = jwtTokenizer.getUserIdFromToken(accessToken);
        log.info(String.valueOf(userIdFromToken));
    }
}
