package com.example.friendapp.repository;

import com.example.friendapp.domain.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends CrudRepository<Friend, Long>, PagingAndSortingRepository<Friend, Long> {

}
