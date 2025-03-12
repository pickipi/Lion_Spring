package com.example.meetingproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
@Table(name = "meetings")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meeting_name", unique = true, nullable = false)
    private String name; // 모임 이름

    @Column(nullable = true) // 모임 생성 시 설명 제공하지 않아도됨
    private String description;

    @Column(nullable = false) // 최대 참가자 수 필수
    private int maxParticipants; // 모임 최대 참가자 수

    private LocalDateTime createdAt; // 모임 생성날짜
    private LocalDateTime updatedAt; // 모임 수정날짜

    // 누구에 의해 만들어졌는지 - 모임 생성한 사람
    @ManyToOne // 한 사람이 여러 모임 가능
    @JoinColumn(name = "created_by", nullable = false) // 외래 키 설정
    private User createdBy;
//    Meeting 엔티티의 createdBy 필드는 User 엔티티를 참조하므로,
//    JPA는 이를 기반으로 두 테이블 간의 외래 키 관계를 자동으로 생성
//    즉, createdBy 필드는 users 테이블의 id 컬럼과 연결
}
