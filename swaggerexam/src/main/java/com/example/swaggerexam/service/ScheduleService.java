package com.example.swaggerexam.service;

import com.example.swaggerexam.domain.*;
import com.example.swaggerexam.repository.MeetingRepository;
import com.example.swaggerexam.repository.ScheduleMemberRepository;
import com.example.swaggerexam.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMemberRepository scheduleMemberRepository;
    private final MeetingRepository meetingRepository;

    // 일정 생성
    @Transactional
    public Schedule createSchedule(Schedule schedule, User creator) {
        schedule.setCreatedBy(creator); // 일정 생성자 자동 등록
        return scheduleRepository.save(schedule);
    }

    // 일정 참가
    @Transactional
    public void joinSchedule(Schedule schedule, User user) {
        // 이미 참가한 사용자 확인
        boolean alreadyJoined = scheduleMemberRepository.existsByScheduleAndUser(schedule, user);
        if (alreadyJoined) {
            throw new IllegalArgumentException("이미 이 일정에 참가한 사용자 ["+user+"]입니다.");
        }

        // ScheduleMember 생성 및 저장
        ScheduleMember scheduleMember = new ScheduleMember();
        scheduleMember.setSchedule(schedule);
        scheduleMember.setUser(user);
        scheduleMemberRepository.save(scheduleMember);
    }

    // 일정 탈퇴
    public void leaveSchedule(Schedule schedule, User user) {
        ScheduleMember scheduleMember = scheduleMemberRepository.findByScheduleAndUser(schedule, user)
                .orElseThrow(() -> new IllegalArgumentException("["+user+"]가 참가한 일정 정보를 찾을 수 없습니다."));
        scheduleMemberRepository.delete(scheduleMember);

        // 참가자 수가 0명이면 일정 삭제
        if (scheduleMemberRepository.countBySchedule(schedule) == 0) {
            scheduleRepository.delete(schedule);
        }
    }

    // 일정 참가자 조회
    public List<User> getScheduleParticipants(Schedule schedule) {
        return scheduleMemberRepository.findBySchedule(schedule)
                .stream()
                .map(ScheduleMember::getUser)
                .collect(Collectors.toList());
    }

    // 일정 조회 (ID 기준)
    public Schedule findById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("ID ["+scheduleId+"]의 일정을 찾을 수 없습니다."));
    }

    // 특정 모임의 모든 일정 조회
    public List<Schedule> findSchedulesByMeetingId(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 모임을 찾을 수 없습니다."));

        return scheduleRepository.findByMeeting(meeting);
    }

    // 일정의 상태 수정
    public void updateScheduleStatus(Schedule schedule, User user, ScheduleStatus newStatus) {
        ScheduleMember scheduleMember = scheduleMemberRepository.findByScheduleAndUser(schedule, user)
                .orElseThrow(() -> new IllegalArgumentException("일정에 참가한 기록이 없습니다."));

        scheduleMember.setStatus(newStatus);
        scheduleMemberRepository.save(scheduleMember);
    }
}

