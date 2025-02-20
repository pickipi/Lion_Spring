package com.example.springdatajpa3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criteria API를 사용한 사용자 조회 예제
        List<User> usersByNameCriteria = userRepository.findUsersByName("홍길동");
        usersByNameCriteria.forEach(user -> log.info("Criteria API로 찾은 사용자: " + user.getName() + ", 이메일: " + user.getEmail()));

        // Criteria 활용 이름, 이메일 주소에 따른 사용자 조회
        userRepository.findUsersDynamically("Milos", null)
                .forEach(user -> log.info("user :: {} {}", user.getName(), user.getEmail()));

        // 예상 데이터 : Milos / Milos@exam.com
        // 예상 데이터 : Milos / Milos@example.com 두개가 있을때
        // 실행 시 Milos가 유저 이름인 이메일 2개가 모두 나옴
        // 만약 email:null이 아닌 exam.com 으로 바꿔준 후
        // 실행 시 Milos가 유저 이름이고 이메일이 email이 exam.com인 값
        // [예상 출력] - user :: Milos Milos@exam.com 이 출력될 것
        // -> 만약 and()가 아닌 or()였으면 Milos, rogers@exam.com 을 입력했을때
        // [예상 출력] - user :: Milos Milos@exam.com, user :: Milos Milos@example.com, user :: Morgan rogers@exam.com
        // 이 처럼 출력될 것. OR연산은 둘 중 하나라도 맞으면 모두 가져오기때문에 이름이 Milos인 데이터들과, 이메일이 rogers@exam.com인 데이터를
        // 모두 가져오는 것이다.

    }
}
