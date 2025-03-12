package com.example.swaggerexam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter@Setter
@Table(name = "schedules")
public class Schedule {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schedule_title", nullable = false)
    private String title; // 일정 제목

    @Column(name = "schedule_date", nullable = false)
    private LocalDate date; // 일정 날짜

    @JsonFormat(pattern = "HH:mm") // ex. "12:00"형태를 표현하기 위해 직렬화/역직렬화가 가능한 @JsonFormat 사용
    @Column(name = "schedule_time",nullable = false)
    private LocalTime time; // 일정 시간, ex. LocalTime으로 LocalTime.of(12, 0) >> "12:00"

    @Column(nullable = false)
    private String location; // 일정 장소

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false) // 모임 ID (FK)
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false) // 생성자 (FK)
    private User createdBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
