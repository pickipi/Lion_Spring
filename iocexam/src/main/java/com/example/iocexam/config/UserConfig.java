package com.example.iocexam.config;

import com.example.iocexam.controller.UserController;
import com.example.iocexam.repository.UserDao;
import com.example.iocexam.repository.UserDaoImpl;
import com.example.iocexam.service.UserService;
import com.example.iocexam.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// UserConfig Spring컨테이너에서 모든 Bean이 등록되고 관리 (설정 클래스)
// = 모든 클래스와 의존성을 @Bean으로 설정하고 의존성 주입을 관리함
@Configuration
public class UserConfig {
    @Bean
    public UserDao userDao(){ // UserDao Bean으로 등록
        return new UserDaoImpl(); // UserDao 인터페이스의 구현체
    }

    @Bean
    public UserService userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao()); // UserDao 의존성 주입
        return userService;
    }

    @Bean
    public UserController userController() {
        UserController userController = new UserController();
        userController.setUserService(userService()); // UserService 의존성 주입
        return userController;
    }
}
