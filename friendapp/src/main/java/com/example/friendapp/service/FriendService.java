package com.example.friendapp.service;

import com.example.friendapp.domain.Friend;
import com.example.friendapp.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    @Transactional(readOnly = true)
    public Iterable<Friend> findAllFriend(){
        return friendRepository.findAll();
    }


    public Friend addFriend(Friend friend){ // Friend 반환 타입 : 친구등록 저장 후 id값이 새로생겨야함
        return friendRepository.save(friend);
    }

    public Friend updateFriend(Friend friend){ // Friend 반환 타입 : 친구등록 저장 후 id값이 새로생겨야함
        return friendRepository.save(friend);
    }

    // Long타입의 id값을 받아 Friend로 반환해주는 메소드를 구현 (상세페이지 관련 메소드)
    @Transactional(readOnly = true)
    public Friend findFriendById(Long id){
        return friendRepository.findById(id).orElse(null);
    }

    // 삭제 메소드 구현
    @Transactional
    public void deleteFriendById(Long id){
        friendRepository.deleteById(id);
    }
}
