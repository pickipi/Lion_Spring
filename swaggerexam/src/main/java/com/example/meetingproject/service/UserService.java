package com.example.meetingproject.service;

import com.example.meetingproject.domain.User;
import com.example.meetingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 사용자 회원 가입
    public User registerUser(String email, String password){
        if(userRepository.findByEmail(email).isPresent()){ // 이메일로 조회 후 이메일 중복 체크
            throw new IllegalArgumentException("["+email+"]이미 존재하는 이메일입니다.");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }

    // 사용자 조회 (이메일 기준)
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("["+email+"]에 해당하는 유저를 찾을 수 없습니다."));
    }
}
