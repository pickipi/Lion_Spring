package com.example.springdatajpa2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionTemplate;

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
			customerService.getCustomer(1L);

			// 2-2) 해결방법. TransactionTemplate 사용
			transactionTemplate.execute(status -> {
				Customer customer = repository.findById(1L).get();
				log.info(customer.getName());
				customer.getOrders().forEach(order -> log.info(order.getProduct()));
				return null;
			});
		};
	}

	@Bean
	public CommandLineRunner run(TransactionTemplate transactionTemplate){
		return args->{
			/*
			Customer customer = repository.findById(1L).get(); // 이때 트랜잭션이 이미 종료됨

			List<Order> orders = customer.getOrders(); // 트랜잭션이 이미 종료되었으므로 LAZY LOADING (지연 로딩)이 일어나게되어
			// 오류가 발생하는 것이다. (=customer로부터 가져올 데이터가 없는 것이다.)
			 */
			customerService.getCustomer(1L);
		};
	}
}
