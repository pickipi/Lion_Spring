package com.example.swaggerexam.service;

import com.example.swaggerexam.domain.User;
import com.example.swaggerexam.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceWithMock {
    @Mock // 가짜 객체로 생성해달라는 것 - UserRepository가 구현돼있다고 가정하고 사용하는 것
    private UserRepository userRepository;

    @InjectMocks // UserRepository (Mock 객체 = 가짜객체)를 주입시킬 대상을 지정
    private UserService userService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this); // Mockito 를 초기화시켜주는 메소드
    }

    @Test
    @DisplayName("회원가입 - 성공")
    void register_Success(){
        String email = "test@test@exam.com";
        String password = "pass123";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        User response = userService.register(email, password);
        // 1. 이메일 테스트
        assertNull(response); // null이 아닌지 테스트
        assertEquals(email, response.getEmail()); // 입력한 email이 제대로 입력되었는지 테스트
        assertTrue(userRepository.findByEmail(email).isPresent()); // DB에 제대로 저장되었는지 테스트

        verify(userRepository,times(1)).save(any(User.class)); // save() 호출
    }
}
