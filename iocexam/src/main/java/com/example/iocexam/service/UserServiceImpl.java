package com.example.iocexam.service;

import com.example.iocexam.domain.User;
import com.example.iocexam.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// UserDao를 주입받음 (비즈니스 로직 수행 및 데이터 계층(UserDao) 호출)
@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    // UserController 클래스 -> UserService 인터페이스로부터 주입받은 User객체를 처리
    @Override
    public void joinUser(User user) {
        userDao.addUser(user); // 유저를 넣어보내 저장하는 메소드
        // UserDao 인터페이스의 메소드 addUser()호출
        // UserDao인터페이스를 구현한 UserDaoImpl 구현체 클래스로 의존성이 주입되어 (>> addUser()의 정보가 전달됨)
    }
}
