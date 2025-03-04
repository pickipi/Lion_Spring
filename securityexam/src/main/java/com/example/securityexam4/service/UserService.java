package com.example.securityexam4.service;

import com.example.securityexam4.domain.Role;
import com.example.securityexam4.domain.User;
import com.example.securityexam4.repository.RoleRepository;
import com.example.securityexam4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // - 회원가입
    @Transactional
    public User registUser(User user){
        // Role정보를 User엔티티에 채워줌
        // 회원가입 요청 시 User 권한으로 가입 (roleRepository 활용)
        Role userRole = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singleton(userRole));

        // 패스워드 : 반드시 인코딩(=암호화)되어야함 (PasswordEncoder 활용)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // - username에 해당하는 사용자가 있는지 체크
    public boolean existsUser(String username){
        return userRepository.existsByUsername(username);
    }
}
