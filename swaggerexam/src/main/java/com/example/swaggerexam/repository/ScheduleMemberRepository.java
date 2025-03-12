package com.example.swaggerexam.repository;

import com.example.swaggerexam.domain.Schedule;
import com.example.swaggerexam.domain.ScheduleMember;
import com.example.swaggerexam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleMemberRepository extends JpaRepository<ScheduleMember, Long> {
    List<ScheduleMember> findBySchedule(Schedule schedule); // 특정 일정의 참가자 조회

    boolean existsByScheduleAndUser(Schedule schedule, User user); // 일정 참가 여부 확인

    Optional<ScheduleMember> findByScheduleAndUser(Schedule schedule, User user);

    int countBySchedule(Schedule schedule); // 일정 참가자 수 조회
}
