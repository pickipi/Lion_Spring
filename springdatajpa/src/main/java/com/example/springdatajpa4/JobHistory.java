package com.example.springdatajpa4;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter@Setter@NoArgsConstructor
@Table(name = "job_history")
public class JobHistory {
    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Id
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
