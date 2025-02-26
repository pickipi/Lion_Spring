package com.example.restexam.domain;


import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
@Table(name = "products")
public class Product {
    private Long id;
    private String name;
    private double price;


}
