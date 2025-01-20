package com.example.iocexam.repository;

import com.example.iocexam.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

// 주입받지 않고 직접 구현체로 사용 (데이터 저장 및 처리를 담당)
@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    // UserDao 인터페이스의 addUser()메소드를 오버라이딩 (구현체)
    @Override
    public void addUser(User user) { // UserServiceImpl 구현체 클래스로부터 User객체를 전달받아 메시지 출력
        System.out.println(user.getName() + "의 정보가 성공적으로 저장되었습니다.");
    }
}
