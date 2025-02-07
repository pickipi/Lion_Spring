package springdatajdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository){
        return args -> {
            // userRepository.save(new User("Harvey", "Barnes@premier.com"));

            User findUser = userRepository.findById(12L).get(); // 방금 넣은 값의 id가 12이기때문에 테스트
            System.out.println(findUser);

            System.out.println("현재 DB에 저장된 데이터 개수 : " + userRepository.count());

            userRepository.findByName("Harvey").stream()
                    .forEach(System.out::println);

            System.out.println("-------------------------------");
            // 페이지 0번 페이지로 시작하는데 페이지 안에 담길 데이터의 사이즈는 3개로 설정
            PageRequest pageRequest = PageRequest.of(0, 3);
            // findAllUserWithPagination은 Page 타입으로 리턴해주므로
            Page<User> pageUsers = userRepository.findAllUsersWithPagination(pageRequest);
            pageUsers.forEach(System.out::println);
            // 이 출력은 유저 데이터의 가장 처음 3개 데이터가 출력될 것
            // PageRequest.of(1, 5)를 하게되면 첫번째 페이지였던 3개의 데이터의 그 다음 데이터부터 (다음 페이지부터) 5개의 데이터가 출력될 것
        };
    }
}
