package com.example.springdatajpa2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter@ToString
@NoArgsConstructor
@Table(name = "jpa_customer")
public class Customer {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private int age;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
