package com.example.oauthexam.repository;

import com.example.oauthexam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    Optional<User> findByProviderAndSocialId(String provider, String socialId);
    // Provier와 SocialId를 가지고 로그인 가능하게 해야함
}
