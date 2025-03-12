package com.example.swaggerexam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class MeetingMember { // table명 : 기본 (meeting_member)
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 두 엔티티 모두 @ManyToOne : 하나의 모임에 여러 사용자 참여, 하나의 사용자가 여러 모임에 참여 가능
    @ManyToOne // N (모임 멤버) : 1 (모임)
    @JoinColumn(name = "meeting_id", nullable = false) // Meeting 테이블 외래키 (FK)
    private Meeting meeting;

    @ManyToOne // N (모임멤버) : 1 (사용자)
    @JoinColumn(name = "user_id", nullable = false) // User 테이블 외래키 (FK)
    private User user;
}
