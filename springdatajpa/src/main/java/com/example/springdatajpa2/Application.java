package com.example.springdatajpa2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Slf4j
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(TransactionTemplate transactionTemplate, CustomerRepository repository, CustomerService customerService){
		return args->{
			/*
			// 1. 오류 발생 (LAZY LOADING)
			Customer customer = repository.findById(1L).get(); // 이때 트랜잭션이 이미 종료됨

			List<Order> orders = customer.getOrders(); // 트랜잭션이 이미 종료되었으므로 LAZY LOADING (지연 로딩)이 일어나게되어
			// 오류가 발생하는 것이다. (=customer로부터 가져올 데이터가 없는 것이다.)
			 */

			// 2-1) 해결방법. Service클래스에서 @Transactional 메소드 사용
//			customerService.getCustomer(1L);
//
//			// 2-2) 해결방법. TransactionTemplate 사용
//			transactionTemplate.execute(status -> {
//				Customer customer = repository.findById(1L).get();
//				log.info(customer.getName());
//				customer.getOrders().forEach(order -> log.info(order.getProduct()));
//				return null;
//			});

			// 이름 조회
//			List<Customer> findName = repository.findByName("박민수");
//			findName.forEach(customer -> log.info(customer.getName()));
//
//			// 이메일 조회
//			List<Customer> findEmail = repository.findByEmail("choi@example.com");
//			findEmail.forEach(customer -> log.info(customer.getEmail()));
//
//			// 특정 문자 포함하는 이메일 조회
//			List<Customer> containEmail = repository.findByEmailContaining("example");
//			containEmail.forEach((customer -> log.info("[example]을 포함하는 이메일 : " + customer.getEmail())));
//
//			// 고객의 주문수를 조회
//			int findOrdersCount = repository.findOrdersCountByCustomer(1L);
//			log.info("주문 수 : " + findOrdersCount);

			// 고객의 세부 정보와 그 고객의 가장 최근 주문 조회
//			List<Object[]> recentOrders = repository.findRecentOrder(1L);
//
//			for (Object[] result : recentOrders) {
//				Customer customer = (Customer) result[0];  // 첫 번째 값: Customer
//				Order order = (Order) result[1];  // 두 번째 값: 최근 Order
//
//				log.info("고객의 이름 : " + customer.getName());
//				log.info("고객의 이메일 : " + customer.getEmail());
//				log.info("고객의 나이 : " + customer.getAge());
//
//				log.info("이 고객의 가장 최근 주문 : " + order.getProduct());
//				log.info("=========================================");
//			}

			// 평균 나이보다 많은 고객을 조회
//			List<Customer> olderCustomers = repository.findCustomersOlderThanAverage();
//			olderCustomers.forEach(customer -> log.info("[평균나이 초과 고객] : " + customer.getName()));

			// 페이지 객체 적용한 특정 문자 포함 이메일 조회
			Pageable pageable = PageRequest.of(0, 3);
			List<Customer> emailContainingPage = repository.findByEmailContaining("example", pageable);
			emailContainingPage.forEach(customer -> log.info(customer.getEmail()));
		};
	}
}
