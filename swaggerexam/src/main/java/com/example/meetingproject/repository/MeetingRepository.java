package com.example.meetingproject.repository;

import com.example.meetingproject.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    Optional<Meeting> findByName(String name);
}
