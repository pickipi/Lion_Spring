package com.example.oauthexam.service;

import com.example.oauthexam.dto.SocialUserRequestDto;
import com.example.oauthexam.entity.User;
import com.example.oauthexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder; // 순환참조 문제 발생
    @Transactional
    public User saveUser(SocialUserRequestDto requestDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setSocialId(requestDto.getSocialId());
        user.setProvider(requestDto.getProvider());
        user.setPassword(passwordEncoder.encode("")); // 소셜 로그인으로 진행하는 사용자는 비밀번호를 비워둠

        

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }


    @Transactional(readOnly = true)
    public Optional<User> findByProviderAndSocialId(String provider, String socialId){
        return userRepository.findByProviderAndSocialId(provider, socialId);
    }
    // 최초의 사용자에 대해서는 추가적인 회원가입 정보를 위하여 회원가입으로 이동하고
    // 그 이외로 로그인하게 될때는 이미 저장되어있을 수 있으므로 Dto로 가져와서 꺼내온 후 나머지 값만 입력받도록 한다.
}
