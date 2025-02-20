package com.example.springdatajpa4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner run(EmployeeRepository employeeRepository){
        return args -> {
            List<Employee> findLastName = employeeRepository.findByLastName("Patel");
            findLastName.forEach(employee -> log.info("[마지막 이름이 Patel]인 고객 정보 :: {} {} 님", employee.getFirstName(), employee.getLastName()));
        };
    }
}
