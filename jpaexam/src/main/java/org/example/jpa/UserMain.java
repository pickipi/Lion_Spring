package org.example.jpa;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserMain {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // 데이터 조회
        User user = userDAO.findUser(2L);
        System.out.println(user);
        log.info("=====[문자열 log]=====");
        log.info("[문자열 + 객체] - find user : {}", user);
        log.info("[문자열 + 객체] - find user : {}{}", user.getName(), user.getEmail());
        // 이처럼 {}에 값들을 매핑시킬 수 있다.

    }
}
