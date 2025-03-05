package com.example.jwtexam.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

// JWT토큰을 편하게 사용하기 위한 Util 클래스
@Component
public class JwtTokenizer {
    private final byte[] accessSecret;
    private final byte[] refreshSecret;

    // 매번 액세스 토큰, 리프레시 토큰이 생성마다 유지시간이 달라지면 안되므로 static 상수로 선언하여 정의해서 사용하도록 함
    public static final Long ACCESS_TOKEN_EXPIRE_COUNT = 1000L * 60 * 30; // 액세스 토큰 유지시간 30분
    public static final Long REFRESH_TOKEN_EXPIRE_COUNT = 1000L * 60 * 60 * 24 * 7; // 액세스 토큰 유지시간 7일



    // 이 accessSecret과 refreshSecret을 환경변수 파일 (application.yml)에서 꺼내서 사용할 것
    public JwtTokenizer(@Value("${jwt.secretKey}") String accessSecret, @Value("${jwt.refreshKey}") String refreshSecret) {
        this.accessSecret = accessSecret.getBytes(StandardCharsets.UTF_8); // String -> byte로 변환
        this.refreshSecret = refreshSecret.getBytes(StandardCharsets.UTF_8); // String -> byte로 변환
    }

    // 토큰에 포함할 정보를 함께 만듦
    private String createToken(Long id, String email, String name,
                               String username, List<String> roles,
                               Long expire, byte[] secretKeys){
        Claims claims = Jwts.claims().setSubject(email);
        // id, name 등 원하는 것을 넣어주면 됨 -> 가급적이면 고유한 식별자값 (중복값이 아닌)을 넣어주는 것이 좋음
        // (이메일도 중복되지 않으므로 넣어준 것)

        // 필요한 정보들 저장
        claims.put("username", username);
        claims.put("name", name);
        claims.put("userId", id);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expire)) // expire : 1000ms (1 sec) >> 1000 * 60 * 60 = 1 hour
                .signWith(getSigningKey(secretKeys))
                .compact();
    }

    private static Key getSigningKey(byte[] secretKey){
        return Keys.hmacShaKeyFor(secretKey);
    }

    // 액세스 토큰 생성
    public String createAccessToken(Long id, String email, String name,
                                    String username, List<String> roles){

        return createToken(id, email, name, username, roles, ACCESS_TOKEN_EXPIRE_COUNT, accessSecret);
    }

    // 리프레시 토큰 생성
    public String createRefreshToken(Long id, String email, String name,
                                    String username, List<String> roles){

        return createToken(id, email, name, username, roles, REFRESH_TOKEN_EXPIRE_COUNT, refreshSecret);
    }
}
