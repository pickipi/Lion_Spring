package com.example.securityexam4.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "lions_users")
@Getter@Setter
public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username; // isak99

    @Column(nullable = false, length = 100)
    private String password; // 1234

    @Column(nullable = false, length = 50)
    private String name; // 이삭

    @Column(nullable = false, length = 100)
    private String email; // isak@premier.com

    // updatable을 false로 설정하여 수정이 되지 않도록 설정
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    // User에서 Role을 얻어오는 부분 - 중복되지 않으므로 Set으로 받아옴
    // FetchType.EAGER : User정보를 꺼낼때 Security가 Role정보까지 꺼내오도록 구현
    // @JoinTable : ForeignKey를 가질 수 없으므로 조인을 이용해서 Role 테이블에 접근
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
