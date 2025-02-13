package org.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Table(name = "schools")
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class School {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "school")
    private List<Student> students = new ArrayList<>();

    public School(String name) {
        this.name = name;
    }
}
