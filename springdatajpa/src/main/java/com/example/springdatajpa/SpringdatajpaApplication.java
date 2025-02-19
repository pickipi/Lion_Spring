package com.example.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringdatajpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository repository){
		return args->{
			// 테스트할 일
			// CREATE
//			User user = new User("Dan", "Burn@premier.com");
//			repository.save(user);
//			log.info("[CREATE] 사용자 등록 : " + user.getName());

			// DELETE
//			User delUser = repository.findById(14L).get();
//			repository.delete(delUser);

			// UPDATE
//			User updateUser = repository.findById(12L).get();
//			updateUser.setName("James");
//			updateUser.setEmail("McConnell@premier.com");
//			repository.save(updateUser);

			// SELECT
//			repository.findAll().forEach(user -> log.info(user.toString()));

			// 이름 기준 조회 메소드 테스트
			List<User> users = repository.findByName("Luke");
			users.forEach(user -> log.info(user.toString()));
		};
	}
}
