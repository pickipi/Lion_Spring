package springdatajdbc02;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void createAndUpdateUser(String name, String email, String newEmail){
        // 1. 값 입력
        jdbcTemplate.update("INSERT INTO users(name, email) VALUES(?,?)", name, email);

        // 테스트를 위한 예외발생시키기
        if(newEmail.contains("error")){
            // error라고 newEmail에 들어왔으면
            throw new RuntimeException("error");
        }

        // 2. 값 수정
        jdbcTemplate.update("UPDATE users SET email = ? WHERE name = ?", newEmail, name);
    }

    // 이 위에 있던 createAndUpdateUser()메소드 코드를 아래처럼 2개의 메소드로 분리한 후 @Transactional 어노테이션 정의
    @Transactional
    public void addUser(String name, String email){
        jdbcTemplate.update("INSERT INTO users(name, email) VALUES(?,?)", name, email);
    }

    @Transactional
    public void updateUser(String name, String newEmail){
        jdbcTemplate.update("UPDATE users SET email = ? WHERE name = ?", newEmail, name);
    }
}
