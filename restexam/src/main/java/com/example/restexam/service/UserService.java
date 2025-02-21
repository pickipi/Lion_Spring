package com.example.restexam.service;

import com.example.restexam.domain.User;
import com.example.restexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 사용자 저장 (CREATE)
    public User saveUser(String email, String name){
        User user = new User(email, name);
        return userRepository.save(user);
    }

    // 모든 사용자 조회 (SELECT - READ)
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // 특정 사용자 조회 (SELECT - READ)
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    // JPA가 자동으로 만들어주는 findById()를 이용해서 조회해볼 수도 있음
//    public User findById(Long id){
//        return userRepository.findById(id).orElseThrow();
//    }

    // 사용자 삭제 (DELETE)
    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 사용자가 업습니다.");
        }
        userRepository.deleteById(id);
    }
}
