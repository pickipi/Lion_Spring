package com.example.iocexam;

import com.example.iocexam.config.UserConfig;
import com.example.iocexam.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserExam {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);

        UserController controller = context.getBean(UserController.class);
        controller.joinUser();

        // joinUser()를 호출하면
        // UserController에서 joinUser()를 호출하여 UserService의 joinUser()로 전달되고, 이 SErvice를 구현하고 있는 repository의
        // userDaoImpl의 addUser메소드를 호출하여
        // "user + 의 정보가 잘 저장되었습니다" 라고 출력될 수 있도록 Bean을 등록해보기
    }
}
