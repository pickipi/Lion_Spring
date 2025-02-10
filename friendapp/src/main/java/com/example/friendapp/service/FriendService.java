package com.example.friendapp.service;

import com.example.friendapp.domain.Friend;
import com.example.friendapp.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Transactional(readOnly = true)
    public Page<Friend> findAllFriend(Pageable pageable){
        // 현재 원하는 페이지 번호가 무엇인지 얻어오는 것
        // getPageSize() : 한 페이지에 몇개씩 가져올지 controller를 통해서 가져옴
        Pageable pageable2 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));

        // pageable2로 정보를 얻은 후 friendRepository의 메소드 findAll에 전달
        return friendRepository.findAll(pageable2);
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
