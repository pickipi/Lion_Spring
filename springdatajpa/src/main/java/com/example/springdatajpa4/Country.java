package com.example.springdatajpa4;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@NoArgsConstructor
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "country_id")
    private String id;

    @Column(name = "country_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}
