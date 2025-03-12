package com.example.swaggerexam.repository;

import com.example.swaggerexam.domain.Meeting;
import com.example.swaggerexam.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByMeeting(Meeting meeting); // 특정 모임에 속한 일정 조회
}
