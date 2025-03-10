package com.example.oauthexam.service;

import com.example.oauthexam.entity.SocialUser;
import com.example.oauthexam.repository.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialUserService {
    private final SocialUserRepository socialUserRepository;

    // 소셜에서 보내준 정보를 저장하기 위한 메소드
    @Transactional
    public SocialUser saveOrUpdateUser(String socialId, String provider,
                                       String username, String email,
                                       String avatarUrl){
        Optional<SocialUser> existUser = socialUserRepository.findBySocialIdAndProvider(socialId, provider);
        SocialUser socialUser;

        if(existUser.isPresent()){
            // 1. 이미 소셜정보를 가진 사용자라면
            socialUser = existUser.get();
            socialUser.setUsername(username);
            socialUser.setEmail(email);
            socialUser.setAvatarUrl(avatarUrl);
        }else{
            // 2. 처음으로 방문한 사용자라면
            socialUser = new SocialUser();
            socialUser.setSocialId(socialId);
            socialUser.setUsername(username);
            socialUser.setEmail(email);
            socialUser.setAvatarUrl(avatarUrl);
            socialUser.setProvider(provider);
        }
        return socialUserRepository.save(socialUser);
    }
}
