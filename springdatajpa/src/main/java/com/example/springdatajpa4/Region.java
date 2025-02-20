package com.example.springdatajpa4;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@NoArgsConstructor
@Table(name = "regions")
public class Region {
    @Id
    @Column(name = "region_id")
    private Integer id;

    @Column(name = "region_name")
    private String name;
}
