package com.example.springdatajpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    // 고급 쿼리 생성 (JPQL)
    @Query("SELECT u FROM User u Where u.name = :name")
    List<User> advancedSelectUser(@Param("name") String name); // 이 @Param의 name이 JPQL에서의 :name에 매핑될 것

    // 고급 쿼리 생성 (JPQL) - LIKE
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> advancedSelectUserLike(@Param("name") String name);

    // 고급 쿼리 (JPQL) - @Modifying 데이터 삭제
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.email = :email")
    int deleteByEmail(@Param("email") String email);

    // 고급 쿼리 (JPQL) - @Modifying 데이터 수정
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id=:id")
    int updateByEmail(@Param("id") Long id, @Param("email") String email);

    // 고급 쿼리 (SQL = nativeQuery) 사용 - 특정 이메일을 포함하는 데이터 조회
    @Query(nativeQuery = true, value = "SELECT * FROM jpa_user WHERE email LIKE CONCAT('%', :email, '%')")
    List<User> selectByEmailNative(@Param("email") String email);

    // Pageable 사용
    List<User> findByNameContaining(String name, Pageable pageable);

}
