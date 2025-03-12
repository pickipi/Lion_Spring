package com.example.meetingproject.service;

import com.example.meetingproject.domain.Meeting;
import com.example.meetingproject.domain.MeetingMember;
import com.example.meetingproject.domain.User;
import com.example.meetingproject.repository.MeetingMemberRepository;
import com.example.meetingproject.repository.MeetingRepository;
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

    public Optional<Meeting> findMeetingByName(String name){
        return meetingRepository.findByName(name); // 특정 모임을 이름으로 검색
    }
}
