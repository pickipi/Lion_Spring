package com.example.securityexam4.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이 값은 유니크해야함
    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
