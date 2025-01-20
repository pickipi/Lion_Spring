package com.example.iocexam;

import com.example.iocexam.config.UserConfig;
import com.example.iocexam.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// UserController를 주입받음
public class UserExam {
    public static void main(String[] args) {
        // UserConfig 클래스의 Bean들을 스캔하여 컨테이너에 등록
        ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);

        // UserController Bean을 컨테이너로부터 가져옴.
        // UserController는 UserService 의존성이 주입된 상태로 반환
        UserController controller = context.getBean(UserController.class);

        // UserController의 joinUser()메소드로부터 UserService 인터페이스에 요청을 전달
        // UserService인터페이스를 구현한 UserServiceImpl 구현체 클래스로 의존성이 주입되어 (>> joinUser()의 정보가 전달됨)
        controller.joinUser();
    }
}
