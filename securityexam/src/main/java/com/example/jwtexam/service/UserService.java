package com.example.jwtexam.service;

import com.example.jwtexam.domain.Role;
import com.example.jwtexam.domain.User;
import com.example.jwtexam.dto.UserRegisterDTO;
import com.example.jwtexam.repository.RoleRepository;
import com.example.jwtexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

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

    // registUser 메소드 오버로딩
    @Transactional
    public User registUser(UserRegisterDTO registerDTO){
        log.info("회원가입 요청 처리 중: {}", registerDTO.getUsername()); // 회원가입 요청 로그 추가

        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
        log.info("비밀번호 암호화 완료");

        Set<Role> roles = registerDTO.getRoles().stream()
                .map(roleRepository::findByName)
                .flatMap(Optional::stream) // Optional이 비어있다면 무시 후 값만 추출
                .collect(Collectors.toSet());
        log.info("역할 정보 설정 완료: {}", roles);

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(encodedPassword);
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setRoles(roles);
        log.info("User 객체 생성 완료: {}", user);

        User savedUser = userRepository.save(user);
        log.info("DB 저장 완료: {}", savedUser);

        return savedUser;
    }

    // - username에 해당하는 사용자가 있는지 체크
    public boolean existsUser(String username){
        return userRepository.existsByUsername(username);
    }
}
