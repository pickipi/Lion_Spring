package com.example.springdatajpa4;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter@Setter@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "department_id")
    private Long id;

    @Column(name = "department_name")
    private String name;

    // foreignKey = @ForeignKey(name = "FK_department_manager"), nullable = true
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;
}
