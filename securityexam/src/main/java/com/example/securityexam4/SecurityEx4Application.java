package com.example.securityexam4;

import com.example.securityexam4.domain.Role;
import com.example.securityexam4.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class SecurityEx4Application {
    public static void main(String[] args) {
        SpringApplication.run(SecurityEx4Application.class);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository){
        return args -> {
            if(roleRepository.count() == 0){ // DB에 저장된 권한이 없다면
                Role userRole = new Role();
                userRole.setName("USER"); // USER라는 권한을 생성

                Role adminRole = new Role();
                adminRole.setName("ADMIN"); // ADMIN이라는 권한을 생성

                roleRepository.saveAll(List.of(userRole, adminRole)); // 생성한 권한들을 모두 DB에 저장
                log.info("USER, ADMIN 권한이 추가되었습니다. ::: {}, {}", userRole, adminRole);
            }else{
                log.info("권한 정보가 이미 존재합니다.");
            }

//            User user = userRepository.findByUsername("테스트");
//            log.info("[테스트]라는 이름의 유저 : " + user.toString());
        };
    }
}
