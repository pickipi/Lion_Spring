package com.example.jwtexam.controller;

import com.example.jwtexam.domain.RefreshToken;
import com.example.jwtexam.domain.Role;
import com.example.jwtexam.domain.User;
import com.example.jwtexam.dto.UserLoginResponseDto;
import com.example.jwtexam.jwt.util.JwtTokenizer;
import com.example.jwtexam.security.dto.UserLoginDto;
import com.example.jwtexam.service.RefreshTokenService;
import com.example.jwtexam.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/api/info")
    public String info(){
        return "info";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response){
        // 1. username이 서버에 존재하는지 체크
        User user = userService.findByUsername(userLoginDto.getUsername());
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 아이디 입니다.");
        }

        // 2. 비밀번호 비교
        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 올바르지 않습니다.");
        }

        // 3. 유저, 비밀번호를 확인했다면 권한 얻어오기
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        // 4. 토큰 발급 (만들어놓은 roles를 5번째 인자로 넣음)
        String accessToken = jwtTokenizer.createAccessToken(user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUsername(),
                roles);

        String refreshToken = jwtTokenizer.createRefreshToken(user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUsername(),
                roles);

        // 5. Refresh Token을 데이터베이스에 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setValue(refreshToken);
        refreshTokenEntity.setUserId(user.getId());

        refreshTokenService.addRefreshToken(refreshTokenEntity);

        // 5-1) 쿠키에도 저장
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        // http에서만 사용가능 (보안 설정) = 쿠키 값을 자바스크립트 등에서는 접근 불가
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        // 쿠키의 시간단위 : 초(sec) 단위, ACCESS_TOKEN_EXPIRE_COUNT는 Long타입에 밀리초(ms) 단위
        // 이 둘을 맞춰주어야함 -> Math.toIntExact : Long타입을 int타입으로 안전하게 바꿔줌
        // /1000 : 초 단위로 맞춰줌
        accessTokenCookie.setMaxAge(Math.toIntExact(JwtTokenizer.ACCESS_TOKEN_EXPIRE_COUNT/1000));

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(Math.toIntExact(JwtTokenizer.REFRESH_TOKEN_EXPIRE_COUNT/1000));

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        // 6. 응답으로 보낼 값 준비
        UserLoginResponseDto loginResponseDto = UserLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .name(user.getName())
                .build();

        return ResponseEntity.ok(loginResponseDto);
    }

    // AccessToken만료 전 RefreshToken을 통해 검증 후 AccessToken 재발급
//    @PostMapping("refreshToken")
//    public ResponseEntity<?> requestRefresh(){ ... }
}
