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

    // 토큰 정보를 꺼내오는 메소드
    public Claims parseToken(String token, byte[] secretKey){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims parseAccessToken(String accessToken){
        return parseToken(accessToken, accessSecret);
    }

    public Claims parseRefreshToken(String refreshToken){
        return parseToken(refreshToken, refreshSecret);
    }

    // DB에서 ID를 통해 토큰 얻어내기
    public Long getUserIdFromToken(String token){
        // Bearer 라는 토큰형태를 사용하여 헤더정보에 보냄 (Bearer 토큰을 이용)
        // [Bearer exJDq23eDAsdnkkqo3SDAllkoisi...] 같은 토큰 형태
        if(token == null || token.isBlank()){
            throw new IllegalArgumentException("JWT 토큰이 없습니다.");
        }

        if(token.startsWith("Bearer ")){ // Bearer 로 시작하는 토큰이 아닌 경우
            throw new IllegalArgumentException("유효하지 않은 형식입니다.");
        }
        Claims claims = parseToken(token, accessSecret);
        if(claims == null){
            throw new IllegalArgumentException("유효하지 않은 형식입니다.");
        }
        Object userId = claims.get("userId"); // claims.put("userId", id) 의 "userID"형식에 맞춰서 꺼내줌
        if(userId instanceof Number){ // userId 타입이 Number인지 검사
            return ((Number)userId).longValue(); // Number이면 long타입으로 형변환 후 리턴
        }else{
            throw new IllegalArgumentException("JWT 토큰에서 userId를 찾을 수 없습니다.");
        }
    }
}
