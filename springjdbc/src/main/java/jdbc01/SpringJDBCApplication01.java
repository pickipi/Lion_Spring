package jdbc01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class SpringJDBCApplication01 implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringJDBCApplication01.class);
    }

    // 간단히 테스트할 것이므로 필드를 통한 주입 방법 채택
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Spring JDBC가 대신해주지 못하는 부분 넣기

        // 입력 (CREATE)
        String sql = "INSERT INTO users(name,email) VALUES(?,?)"; // users테이블에 name, email값 넣기
        int count = jdbcTemplate.update(sql, "Watkins", "Watkins@premier.com");
        System.out.println("데이터베이스에 적용한 횟수 : " + count);

        // 조회 (SELECT)
        List<User> users = jdbcTemplate.query("SELECT id,name,email FROM users", new BeanPropertyRowMapper<>(User.class));
        for(User user : users){
            System.out.println(user);
        }
    }
}
