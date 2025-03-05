package com.example.jwtexam.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
public class JwtExample {
    public static void main(String[] args) {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jwt = Jwts.builder()
                .setIssuer("juunb-app") // 어디서 사용하는 것인지 설정
                .setSubject("juunb123")
                .setExpiration(new Date(System.currentTimeMillis() + 36000)) // 만료 시간 설정 (ms 기준)
                .setAudience("juunb-audience")
                .claim("role", "ADMIN")
                .signWith(secretKey) // SIGN을 만듦 (secretKey를 이용해서 만듦)
                .compact();

        System.out.println(jwt); // 토큰 출력해보기

        // JWT 파싱, 검증 파트
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        log.info("[Decoding Result] - Expiration Time : {}", claims.getExpiration());
        log.info("[Decoding Result] - Subject : {}", claims.getSubject());
        log.info("[Decoding Result] - Role : {}", claims.getAudience());
    }
}
