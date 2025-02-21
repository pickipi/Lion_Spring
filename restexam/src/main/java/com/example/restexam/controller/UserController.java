package com.example.restexam.controller;

import com.example.restexam.domain.User;
import com.example.restexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpa/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 사용자 등록 (POST방식)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user.getEmail(), user.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // 모든 사용자 조회 (GET방식)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 특정 사용자 조회 (GET방식)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // 사용자 삭제 (DELETE)
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
