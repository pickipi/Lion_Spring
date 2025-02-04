package jdbc02.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements UserDao{
    private final JdbcTemplate jdbcTemplate; // final이 붙은 필드
    // private String etc; // final이 붙지 않은 필드

    @Override
    public void insertUser(User user) {
        // 입력 (CREATE)
        String sql = "INSERT INTO users(name,email) VALUES(?,?)"; // users테이블에 name, email값 넣기
        int count = jdbcTemplate.update(sql, user.getName(), user.getEmail());
        System.out.println("데이터베이스에 적용한 횟수 : " + count);
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void updateUserEmail(String name, String email) {
        String sql = "UPDATE users SET email=? where name=?";
        jdbcTemplate.update(sql, email, name); // 위에 정의된 쿼리문의 순서대로 email, name순으로 위치
    }

    @Override
    public void deleteUser(String name) {
        jdbcTemplate.update("DELETE FROM users WHERE name=?",name);
    }
}
