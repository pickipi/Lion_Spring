package com.example.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이름 기준 조회 메소드 추가
    List<User> findByName(String name);
}
