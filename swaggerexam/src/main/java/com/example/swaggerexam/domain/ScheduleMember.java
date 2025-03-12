package com.example.swaggerexam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class ScheduleMember {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false) // 일정 ID (FK)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 사용자 ID (FK)
    private User user;

    @Enumerated(EnumType.STRING) // Enum -> String 가능 (=DB에서 문자열(VARCHAR)로 저장됨)
    @Column(nullable = false)
    private ScheduleStatus status = ScheduleStatus.ATTENDING; // 기본값 : ATTENDING으로 부여
}
