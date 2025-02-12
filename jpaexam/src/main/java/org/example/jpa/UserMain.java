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

        // 엔티티 비교
        User user2 = userDAO.findUser(2L);
        boolean equalsResult = (user == user2);
        log.info("[user == user2] : {}", equalsResult);

        // 데이터 수정
        user2.setName("Rush");
        userDAO.updateUser(user2); // user2의 이름을 변경 후 update를 수행해봄

    }
}
