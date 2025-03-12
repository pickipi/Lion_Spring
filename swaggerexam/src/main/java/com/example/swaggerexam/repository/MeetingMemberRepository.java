package com.example.swaggerexam.repository;

import com.example.swaggerexam.domain.Meeting;
import com.example.swaggerexam.domain.MeetingMember;
import com.example.swaggerexam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {
    List<MeetingMember> findByMeeting(Meeting meeting); // 특정 Meeting에 속한 모든 회원 조회
    List<MeetingMember> findByUser(User user); // 특정 User가 참여중인 모든 모임 조회

    boolean existsByMeetingAndUser(Meeting meeting, User user); // 모임 및 사용자가 존재하는지 true/false
    Optional<MeetingMember> findByMeetingAndUser(Meeting meeting, User user); // 모임정보를 검색
    int countByMeeting(Meeting meeting); // 현재 참가자 수 계산
}
