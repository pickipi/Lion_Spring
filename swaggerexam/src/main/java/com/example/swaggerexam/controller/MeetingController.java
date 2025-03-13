package com.example.swaggerexam.controller;

import com.example.swaggerexam.domain.Meeting;
import com.example.swaggerexam.domain.User;
import com.example.swaggerexam.service.MeetingService;
import com.example.swaggerexam.service.UserService;
import com.example.swaggerexam.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 모임 생성/수정/삭제/참가 관리
@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
@Tag(name = "Meeting", description = "모임 관련 API")
public class MeetingController {
    private final MeetingService meetingService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    // 모임 생성 (로그인한 사용자만)
    @Operation(
            summary = "모임 생성",
            description = "새로운 모임을 생성합니다.",
            tags = {"Meeting"}
    )
    @PostMapping
    public ResponseEntity<Meeting> createMeeting(
            @RequestHeader("Authorization") String token,
            @RequestBody Meeting meeting) {

        Long userId = jwtUtil.validateToken(token.replace("Bearer ", ""));
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findUserById(userId);
        meeting.setCreatedBy(user); // 모임 생성자 지정
        Meeting savedMeeting = meetingService.createMeeting(meeting);
        return ResponseEntity.ok(savedMeeting);
    }

    // 모든 모임 목록 조회
    @Operation(summary = "모든 모임 목록 조회", description = "모든 모임들의 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Meeting>> getMeetings() {
        List<Meeting> meetings = meetingService.getAllMeetings();
        return ResponseEntity.ok(meetings);
    }

    // 모임 참가자 목록 조회 API
    @Operation(summary = "특정 모임 참가자 목록 조회", description = "특정 모임에 참여한 참가자들의 목록을 조회합니다.")
    @GetMapping("/{meetingId}/participants")
    public ResponseEntity<List<User>> getMeetingParticipants(@PathVariable("meetingId") Long meetingId) {
        Meeting meeting = meetingService.findMeetingById(meetingId);
        List<User> participants = meetingService.findParticipantsByMeeting(meeting);
        return ResponseEntity.ok(participants);
    }

    // 모임 참가 API
    @Operation(summary = "모임 참가", description = "사용자가 모임에 참가합니다.")
    @PostMapping("/{meetingId}/join")
    public ResponseEntity<String> joinMeeting(
            @PathVariable("meetingId") Long meetingId,
            @RequestHeader("Authorization") String token) {

        Long userId = jwtUtil.validateToken(token.replace("Bearer ", ""));
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        User user = userService.findUserById(userId);
        Meeting meeting = meetingService.findMeetingById(meetingId);
        meetingService.joinMeeting(meeting, user);
        return ResponseEntity.ok("모임 참가 완료!");
    }


    // 모임 수정 (모임을 만든 생성자만 가능)
    @Operation(summary = "모임 수정(생성자만)", description = "이 모임을 만든 생성자만이 모임을 수정합니다.")
    @PutMapping("/{meetingId}")
    public ResponseEntity<Meeting> updateMeeting(
            @PathVariable("meetingId") Long meetingId,
            @RequestHeader("Authorization") String token,
            @RequestBody Meeting updatedMeeting) {

        Long userId = jwtUtil.validateToken(token.replace("Bearer ", ""));
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findUserById(userId);
        Meeting meeting = meetingService.updateMeeting(meetingId, updatedMeeting, user);
        return ResponseEntity.ok(meeting);
    }

    // 모임 삭제 (모임을 만든 생성자만 가능)
    @Operation(summary = "모임 삭제(생성자만)", description = "이 모임을 만든 생성자만이 모임을 삭제합니다.")
    @DeleteMapping("/{meetingId}")
    public ResponseEntity<Void> deleteMeeting(
            @PathVariable("meetingId") Long meetingId,
            @RequestHeader("Authorization") String token) {

        Long userId = jwtUtil.validateToken(token.replace("Bearer ", ""));
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findUserById(userId);
        meetingService.deleteMeeting(meetingId, user);
        return ResponseEntity.ok().build();
    }
}
