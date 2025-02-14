package org.example.jpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Table(name = "employees")
@Entity
@Getter@Setter@ToString
@NoArgsConstructor
public class Employee {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(
            name = "employees_projects",
            joinColumns = @JoinColumn(name = "employee_id"), // 주된 엔티티쪽의 컬럼을 명시 (Employee의 기본키인 Long id)
            inverseJoinColumns = @JoinColumn(name = "project_id") // 반대쪽의 컬럼을 명시 (Project의 기본키인 Long id)
    )
    private Set<Project> projects= new HashSet<>();
}
