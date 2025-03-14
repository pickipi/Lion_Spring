package com.example.swaggerexam.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtUtil {
    private static final String SECRET = "12345678901234567890123456789012"; // 비밀키
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 토큰의 유효시간 1시간
    private final ConcurrentHashMap<String, Boolean> invalidTokens = new ConcurrentHashMap<>();

    public String generateToken(Long userId) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public Long validateToken(String token) {
        try {
            if (invalidTokens.containsKey(token)) {
                return null; // 로그아웃된 토큰을 처리함
            }
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return Long.parseLong(decodedJWT.getSubject()); // 사용자 ID를 반환
        } catch (JWTVerificationException | NumberFormatException e) {
            return null; // 유효하지 않은 토큰
        }
    }

    public void invalidateToken(String token) {
        invalidTokens.put(token, true);
    }
}