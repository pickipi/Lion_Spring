package com.example.swaggerexam.service;

import com.example.swaggerexam.domain.Meeting;
import com.example.swaggerexam.domain.MeetingMember;
import com.example.swaggerexam.domain.User;
import com.example.swaggerexam.repository.MeetingMemberRepository;
import com.example.swaggerexam.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;

    // 모임 추가 (CREATE)
    public Meeting createMeeting(Meeting meeting){
        // 모임 저장 로직

        return meetingRepository.save(meeting);
    }

    // 모임 삭제 (DELETE)
    public void deleteMeeting(Long meetingId, User currentUser) { // 모임의 id로 검색
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("모임을 찾을 수 없습니다."));

        if (!meeting.getCreatedBy().equals(currentUser)) { // 모임의 생성자가 아닐 경우 삭제 불가 -> 예외 발생
            throw new SecurityException("이 모임의 생성자가 아닙니다.");
        }
        meetingRepository.delete(meeting);
    }

    // 모임 수정 (UPDATE)
    public Meeting updateMeeting(Long meetingId, Meeting updatedMeeting, User currentUser) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("모임을 찾을 수 없습니다."));

        if (!meeting.getCreatedBy().equals(currentUser)) {
            throw new SecurityException("이 모임의 생성자만 수정할 수 있습니다.");
        }

        meeting.setName(updatedMeeting.getName());
        meeting.setDescription(updatedMeeting.getDescription());
        meeting.setMaxParticipants(updatedMeeting.getMaxParticipants());
        return meetingRepository.save(meeting);
    }

    // 모임 참가 - 사용자가 모임에 참가
    public void joinMeeting(Meeting meeting, User user) {
        // 이미 참가한 사용자 확인
        boolean alreadyJoined = meetingMemberRepository.existsByMeetingAndUser(meeting, user);
        if (alreadyJoined) { // exists 결과 -> true -> 이미 참가한 사용자 (alreadyJoined)
            throw new IllegalArgumentException("이미 이 모임에 참가한 사용자 ["+user+"]입니다.");
        }

        // MeetingMember 생성 및 저장
        MeetingMember meetingMember = new MeetingMember(); // 새로운 (모임 멤버)그룹을 만듦
        meetingMember.setMeeting(meeting); // 모임에는 받아온 모임으로 주입
        meetingMember.setUser(user); // 사용자에는 받아온 사용자로 주입
        meetingMemberRepository.save(meetingMember);
    }

    // 모임 탈퇴
    public void leaveMeeting(Meeting meeting, User user) {
        MeetingMember meetingMember = meetingMemberRepository.findByMeetingAndUser(meeting, user)
                .orElseThrow(() -> new IllegalArgumentException("["+user+"]가 참가한 모임 정보를 찾을 수 없습니다."));
        meetingMemberRepository.delete(meetingMember);
    }

    // 모임 목록 조회 (=사용자 생성/참여한 모임)
    public List<Meeting> findAllMeetingsByUser(User user) {
        return meetingMemberRepository.findByUser(user)
                .stream()
                .map(MeetingMember::getMeeting)
                .collect(Collectors.toList());
    }

    // 모임에 참가한 사용자 목록 반환
    public List<User> findParticipantsByMeeting(Meeting meeting) {
        return meetingMemberRepository.findByMeeting(meeting)
                .stream()
                .map(MeetingMember::getUser)
                .collect(Collectors.toList());
    }

    // 현재 참가자 수
    public int getCurrentParticipants(Meeting meeting) {
        return meetingMemberRepository.countByMeeting(meeting);
    }


    // 모임 이름으로 모임 검색
    public Optional<Meeting> findMeetingByName(String name){
        return meetingRepository.findByName(name); // 특정 모임을 이름으로 검색
    }
}
