package com.example.swaggerexam.service;

import com.example.swaggerexam.domain.User;
import com.example.swaggerexam.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 테스트가 끝나면 DB에서 변경했던 사항들을 롤백시켜줌. >> 실제로 DB에 반영하지 않겠다는 것
class UserServiceTest {
    @Autowired
    private UserService userService; // UserService가 필요하기때문에 필드 주입 (@Autowired)

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 - 성공")
    void register_Success() {
        String email = "test111@exam.com";
        String password = "1234";
        User response = userService.register(email, password);

        // 1. 이메일 테스트
        assertNull(response); // null이 아닌지 테스트
        assertEquals(email, response.getEmail()); // 입력한 email이 제대로 입력되었는지 테스트
        assertTrue(userRepository.findByEmail(email).isPresent()); // DB에 제대로 저장되었는지 테스트

        // 2. 패스워드 테스트
        assertTrue(passwordEncoder.matches(password, response.getPassword()));

    }

    @Test
    @DisplayName("회원가입 - 실패 (중복 이메일)")
    void register_EmailFail() {
        String email = "duplicate@exam.com";
        String password = "1234";

        // 기존 사용자 저장
        User existUser = new User();
        existUser.setEmail(email);
        existUser.setPassword(password);

        // 동일한 이메일로 회원가입 테스트
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register(email, "5678");
        });
        assertEquals("[" + email + "]이미 존재하는 이메일입니다.", exception.getMessage());
    }
}