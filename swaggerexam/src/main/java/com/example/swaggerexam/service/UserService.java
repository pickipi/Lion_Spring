package com.example.swaggerexam.service;

import com.example.swaggerexam.domain.User;
import com.example.swaggerexam.repository.UserRepository;
import com.example.swaggerexam.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // 사용자 회원 가입
    @Transactional
    public User register(String email, String password){
        if(userRepository.findByEmail(email).isPresent()){ // 이메일로 조회 후 이메일 중복 체크
            throw new IllegalArgumentException("["+email+"]이미 존재하는 이메일입니다.");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    // 사용자 조회 (이메일 기준)
    @Transactional(readOnly = true)
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("["+email+"]에 해당하는 유저를 찾을 수 없습니다."));
    }

    // 사용자 조회 (ID 기준)
    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("ID ["+userId+"]의 유저를 찾을 수 없습니다."));
    }

    // 로그인
    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) { // 비밀번호 암호화
            return jwtUtil.generateToken(user.get().getId()); // 토큰 발급
        }
        return null;
    }

    // 사용자 인증 (=JWT 토큰 검증)
    public Long validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    // 로그아웃
    public void logout(String token) {
        jwtUtil.invalidateToken(token);
    }
}
