package com.example.springdatajpa4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner run(EmployeeRepository employeeRepository){
        return args -> {
//            List<Employee> findLastName = employeeRepository.findByLastName("Patel");
//            findLastName.forEach(employee -> log.info("[마지막 이름이 Patel]인 사원 정보 :: {} {} 님", employee.getFirstName(), employee.getLastName()));
//
//            // 1. 연봉 12000이상 조회
//            List<Employee> findMoreSalary = employeeRepository.findBySalaryGreaterThanEqual(12000d);
//            findMoreSalary.forEach(employee -> log.info("[연봉 12000 이상]인 사원 : {}의 연봉 [{}]", employee.getFirstName(), employee.getSalary()));

//            // 2. 사원 번호로 조회
//            Optional<Employee> findEmpId = employeeRepository.findById(176);
//            if(findEmpId.isPresent()){
//                Employee employee = findEmpId.get();
//                log.info("[사원번호 176]인 사원 : {}", employee.getLastName());
//            }

            // 3. Fetch Join 변경 후 테스트
//            log.info("Employees in department 1 with salary between 2900 and 3100: Fetch Join ==================");
//            employeeRepository.findByDepartmentIdInAndSalaryBetween(Arrays.asList(30),2900.0, 3100.0)
//                    .forEach(employee -> log.info(employee.toString()));

//             4. 1 + N 테스트
            log.info("Employees in department 1 with salary between 2900 and 3100:");
            employeeRepository.findBySalaryBetween(2900.0,3100.0)
                    .forEach(employee -> log.info(employee.toString()));
        };
    }
}
