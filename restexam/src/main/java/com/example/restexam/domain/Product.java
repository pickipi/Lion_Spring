package com.example.restexam.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
@Table(name = "products")
@Entity
@AllArgsConstructor // DTO를 통해 값을 복사하기 위해선 전체 필드를 가져오는 생성자도 필요
public class Product {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
}
