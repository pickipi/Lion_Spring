package com.example.springdatajpa2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter@Setter@ToString
@NoArgsConstructor
@Table(name = "jpa_order")
public class Order {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order(String product, LocalDate date, Customer customer) {
        this.product = product;
        this.date = date;
        this.customer = customer;
    }
}
