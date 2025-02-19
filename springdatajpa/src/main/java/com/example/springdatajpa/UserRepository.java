package com.example.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이름 기준 데이터 조회 메소드 추가
    List<User> findByName(String name);

    // 이메일 기준 데이터 조회 메소드 추가
    List<User> findByEmail(String email);

    // 특정 이름을 포함하는 데이터 조회 메소드 추가
    List<User> findByNameContaining(String name);

    // 특정 이름으로 시작하는 데이터 조회 메소드 추가
    List<User> findByNameStartingWith(String name);

    // 이름이 (?)이거나 이메일이 (?)인 데이터 조회 메소드 추가
    List<User> findByNameOrEmail(String name, String email);

    // 이름이 (?) 이고, 이메일은 (?)인 데이터 조회 메소드 추가
    List<User> findByNameAndEmail(String name, String email);
}
