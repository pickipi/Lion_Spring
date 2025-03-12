package com.example.meetingproject.repository;

import com.example.meetingproject.domain.Meeting;
import com.example.meetingproject.domain.MeetingMember;
import com.example.meetingproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {
    List<MeetingMember> findByMeeting(Meeting meeting); // 특정 Meeting에 속한 모든 회원 조회
    List<MeetingMember> findByUser(User user); // 특정 User가 참여중인 모든 모임 조회
}
