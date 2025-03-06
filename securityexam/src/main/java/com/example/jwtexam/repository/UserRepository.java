package com.example.jwtexam.repository;

import com.example.jwtexam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username); // 유저가 있는지 boolean값으로 반환 (회원가입 시 활용 가능)
    // 전체 유저정보를 가져올 필요없이 똑같은 이름의 유저가 있는지만 검사하면 되므로 효율적
}
