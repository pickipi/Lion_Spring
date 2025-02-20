package com.example.springdatajpa4;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@NoArgsConstructor
@Table(name = "locations")
public class Location {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer id;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "postal_code")
    private String postalCode;

    private String city;

    @Column(name = "state_province")
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
