package com.example.beforesecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public void test(){
        log.info("[스레드 이름] : " + Thread.currentThread().getName() + " :: [사용자 이름] : " + UserContext.getUser().getName());
    }
}
