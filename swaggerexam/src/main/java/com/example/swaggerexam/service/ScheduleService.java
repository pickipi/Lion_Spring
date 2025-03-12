package com.example.swaggerexam.service;

import com.example.swaggerexam.domain.Schedule;
import com.example.swaggerexam.domain.ScheduleMember;
import com.example.swaggerexam.domain.User;
import com.example.swaggerexam.repository.ScheduleMemberRepository;
import com.example.swaggerexam.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMemberRepository scheduleMemberRepository;

    // 일정 생성
    public Schedule createSchedule(Schedule schedule, User creator) {
        schedule.setCreatedBy(creator); // 일정 생성자 자동 등록
        return scheduleRepository.save(schedule);
    }

    // 일정 참가
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

    // 일정 참가자 조회
    public List<User> getScheduleParticipants(Schedule schedule) {
        return scheduleMemberRepository.findBySchedule(schedule)
                .stream()
                .map(ScheduleMember::getUser)
                .collect(Collectors.toList());
    }
}

