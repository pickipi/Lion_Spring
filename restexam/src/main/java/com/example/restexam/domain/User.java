package com.example.restexam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@NoArgsConstructor
@Table(name = "jpa_user")
public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
