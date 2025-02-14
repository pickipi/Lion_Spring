package org.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@Table(name = "passports")
@Entity
public class Passport {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", unique = true)
    private Person person;

    public Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
