package org.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter@Setter
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode; // 우편번호
    private String country;
}

@Entity
@Table(name = "companies")
@Getter@Setter
class Company{
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private Address address; // 회사는 주소를 가지고 있을 것
}
