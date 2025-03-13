package com.example.swaggerexam.controller;

import com.example.swaggerexam.domain.Meeting;
import com.example.swaggerexam.domain.Schedule;
import com.example.swaggerexam.domain.User;
import com.example.swaggerexam.service.MeetingService;
import com.example.swaggerexam.service.ScheduleService;
import com.example.swaggerexam.service.UserService;
import com.example.swaggerexam.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 일정 생성/참가/탈퇴/상태 변경
@RestController
@RequestMapping("/meetings/{meetingId}/schedules")
@RequiredArgsConstructor
@Tag(name = "Schedule", description = "일정 관련 API")
public class ScheduleController {
    private final UserService userService;
    private final ScheduleService scheduleService;
    private final JwtUtil jwtUtil;
    private final MeetingService meetingService;

    // 일정 생성 (모임 참여자만 가능)
    @Operation(
            summary = "일정 생성",
            description = "새로운 일정을 생성합니다. (모임 참여자만 가능)",
            tags = {"Schedule"})
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(
            @PathVariable("meetingId") Long meetingId,
            @RequestHeader("Authorization") String token,
            @RequestBody Schedule schedule) {

        Long userId = jwtUtil.validateToken(token.replace("Bearer ", ""));
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findUserById(userId);
        Meeting meeting = meetingService.findMeetingById(meetingId); // Meeting 조회

        // 예외 처리
        if (meeting == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 모임이 없을 경우 404 응답
        }
        List<User> participants = meetingService.findParticipantsByMeeting(meeting);
        if (!participants.contains(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 모임 참가자가 아닐 경우 403 응답
        }

        schedule.setMeeting(meeting); // Schedule에 Meeting 설정
        schedule.setCreatedBy(user);
        Schedule savedSchedule = scheduleService.createSchedule(schedule, user);
        return ResponseEntity.ok(savedSchedule);
    }

    // 일정 목록 조회
    @Operation(summary = "특정 모임의 일정 조회", description = "특정 모임의 일정들을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Schedule>> getSchedules(@PathVariable("meetingId") Long meetingId) {
        List<Schedule> schedules = scheduleService.findSchedulesByMeetingId(meetingId);
        return ResponseEntity.ok(schedules);
    }

    // 일정 참가자 목록 조회
    @Operation(summary = "특정 일정의 참가자 목록 조회", description = "특정 모임 일정의 참가자들 목록을 조회합니다.")
    @GetMapping("/{scheduleId}/participants")
    public ResponseEntity<List<User>> getScheduleParticipants(@PathVariable("scheduleId") Long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        List<User> participants = scheduleService.getScheduleParticipants(schedule);
        return ResponseEntity.ok(participants);
    }
}
