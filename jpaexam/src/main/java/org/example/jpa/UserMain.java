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
//        user2.setName("Rush");
//        userDAO.updateUser(user2); // user2의 이름을 변경 후 update를 수행해봄

        // 샘플 데이터 삽입
//        userDAO.createUser(new User("Tyler", "Morton@premier.com"));
//        userDAO.createUser(new User("Antonee", "Robinson@premier.com"));
//        userDAO.createUser(new User("Jayden", "Danns@premier.com"));
//        userDAO.createUser(new User("Sander", "Berger@premier.com"));
//        userDAO.createUser(new User("Milos", "Kerkez@premier.com"));

        // 데이터 삭제 - id로 접근
//        User delUser = userDAO.findUser(4L);
//        userDAO.deleteUser(delUser);

        // JPAUtil 추가 후 createUser()테스트
        userDAO.createUser(new User("Luke", "Chambers@premier.com"));

    }
}
