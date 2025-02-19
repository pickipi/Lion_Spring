package com.example.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

			// 이름 기준 데이터 조회 메소드 테스트
//			List<User> users = repository.findByName("Luke");
//			users.forEach(user -> log.info(user.toString()));

			// 이메일 기준 데이터 조회 메소드 테스트
//			List<User> emails = repository.findByEmail("Chambers@premier.com");
//			emails.forEach(user -> log.info(user.toString()));

			// 특정 이름을 포함하는 데이터 조회 메소드 테스트
//			List<User> containName = repository.findByNameContaining("d"); // d가 포함된 이름 조회
//			containName.forEach(user -> log.info(user.toString()));
//
//			// 특정 이름으로 시작하는 데이터 조회 메소드 테스트
//			List<User> startName = repository.findByNameStartingWith("Ja"); // Ja로 시작하는 이름 조회
//			startName.forEach(user -> log.info(user.toString()));

			// 이름이 (?)이거나 이메일이 (?)인 데이터 조회 메소드 테스트
//			List<User> nameOrEmail = repository.findByNameOrEmail("Andrew", "Berger@premier.com");
//			nameOrEmail.forEach(user -> log.info(user.toString()));

			// 이름이 (?) 이고, 이메일은 (?)인 데이터 조회 메소드 추가
//			List<User> nameAndEmail = repository.findByNameAndEmail("Andrew", "Robertson@premier.com");
//			nameAndEmail.forEach(user -> log.info(user.toString()));

			// 고급 쿼리 JPQL
//			repository.advancedSelectUser("Jayden").forEach(user -> log.info(user.toString()));

			// 고급 쿼리 JPQL - LIKE (%Ja% 로 Ja를 포함하는 것 조회)
//			repository.advancedSelectUserLike("Ja").forEach(user -> log.info(user.toString()));

			// 고급 쿼리 - 데이터 삭제 (@Modifying)
//			int deleteCount = repository.deleteByEmail("Chambers@premier.com");
//			log.info("삭제된 데이터 수 : " + deleteCount);

			// 고급 쿼리 - 데이터 수정 (@Modifying)
//			int updateCount = repository.updateByEmail(13L, "Koumas@premier.com");
//			log.info("수정된 데이터 수 : " + updateCount);

			// 고급 쿼리 - native SQL 사용 - 이메일로 데이터 조회
//			repository.selectByEmailNative("premier").forEach(user -> log.info(user.toString()));

			// Pageable 객체 사용
			Pageable pageable = PageRequest.of(0, 2);
			// 조회할 페이지 0 (첫페이지), 한 페이지당 2개씩 데이터를 보여줄 것
			// 조회할 페이지 1 (두번째페이지)로 그 다음 데이터 2개를 조회할 수 있을 것
			repository.findByNameContaining("J", pageable).forEach(user -> log.info(user.toString()));

			// findAll()메소드 같은 경우 기본적으로 pageable 객체를 받아서 실행하도록 할 수 있으므로
			// repository.findAll(pageable).forEach(user -> log.info(user.toString()));
			// 이처럼 바로 페이지 조회 메소드를 수행하도록 구현할 수 있음

		};
	}
}
