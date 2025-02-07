package springdatajdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository){
        return args -> {
            // userRepository.save(new User("Harvey", "Barnes@premier.com"));

//            User findUser = userRepository.findById(12L).get(); // 방금 넣은 값의 id가 12이기때문에 테스트
//            System.out.println(findUser);
//
//            System.out.println("현재 DB에 저장된 데이터 개수 : " + userRepository.count());
//
//            userRepository.findByName("Harvey").stream()
//                    .forEach(System.out::println);
//
//            System.out.println("-------------------------------");
//            // 페이지 0번 페이지로 시작하는데 페이지 안에 담길 데이터의 사이즈는 3개로 설정
//            PageRequest pageRequest = PageRequest.of(0, 3);
//            // findAllUserWithPagination은 Page 타입으로 리턴해주므로
//            Page<User> pageUsers = userRepository.findAllUsersWithPagination(pageRequest);
//            pageUsers.forEach(System.out::println);
            // 이 출력은 유저 데이터의 가장 처음 3개 데이터가 출력될 것
            // PageRequest.of(1, 5)를 하게되면 첫번째 페이지였던 3개의 데이터의 그 다음 데이터부터 (다음 페이지부터) 5개의 데이터가 출력될 것
        };
    }

    // 배치 (Batch) : 여러 건의 SQL문을 한번에 입력하는 작업 수행
    @Bean
    public CommandLineRunner batchUpdateDemo(JdbcTemplate jdbcTemplate){
        return args -> {
            List<User> users = Arrays.asList(
                    new User("kang","kang@exam.com"),
                    new User("kim","kim@exam.com"),
                    new User("hong","hong@exam.com"),
                    new User("lee","lee@exam.com"),
                    new User("aaa","aaa@exam.com")
            );

            String sql = "INSERT INTO users(name, email) VALUES(?,?)";
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    // 첫번째 인자로 PreparedStatement
                    // JDBC에서 SQL만들고 PreparedStatement 사용한 것처럼
                    User user = users.get(i); // 다수의 데이터를 넣을 리스트에서 i(인덱스)로 접근해서 가져옴
                    ps.setString(1, user.getName()); // 첫번째 물음표(?)에 대한 바인딩 수행
                    ps.setString(2, user.getEmail());// 두번째 물음표(?)에 대한 바인딩 수행
                }

                @Override
                public int getBatchSize() {
                    return users.size(); // 넣을 값이 몇개인지를 리턴함 (다수의 데이터 (5개)이면 5 출력)
                }
            });
        };
    }
}
