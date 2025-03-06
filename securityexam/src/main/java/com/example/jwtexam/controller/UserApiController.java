package com.example.jwtexam.controller;

import com.example.jwtexam.domain.RefreshToken;
import com.example.jwtexam.domain.Role;
import com.example.jwtexam.domain.User;
import com.example.jwtexam.dto.UserLoginResponseDto;
import com.example.jwtexam.jwt.util.JwtTokenizer;
import com.example.jwtexam.security.dto.UserLoginDto;
import com.example.jwtexam.service.RefreshTokenService;
import com.example.jwtexam.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        // 6. 응답으로 보낼 값 준비
        UserLoginResponseDto loginResponseDto = UserLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .name(user.getName())
                .build();

        return ResponseEntity.ok(loginResponseDto);
    }
}
