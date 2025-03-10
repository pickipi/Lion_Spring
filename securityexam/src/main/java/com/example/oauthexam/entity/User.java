package com.example.oauthexam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "lions_users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username; // isak99

    @Column(nullable = false, length = 100)
    private String password; // 1234

    @Column(nullable = false, length = 50)
    private String name; // 이삭

    @Column(nullable = false, length = 100)
    private String email; // isak@premier.com

    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    // 컬럼 추가 - OAuth의 소셜 아이디를 가져오는 부분
    @Column(name = "social_id")
    private String socialId;
    private String provider; // 어느 소셜에서 제공중인지를 가져옴


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
