package springdatajdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Page<User> findAllUsersWithPagination(Pageable pageable) {
        // JdbcTemplate을 이용해서 실제 수행할 쿼리를 만듦
        // 페이지에 관련해서 MySQL은 limit을 제공한다. (어디서부터어디까지 찾아냄)
        // :(콜론)이 들어가면 변수처럼 값이 바뀌어서 그곳에 들어갈 것
        // (=LIMIT을 limit 변수로 바꿔서, OFFSET을 offset 변수로 바꿔서 들어간다는 의미)
        // ? 대신 :(콜론)을 쓰는 이유 : 그 값을 대치해서 넣어달라
        String query = "SELECT id, name, email FROM users LIMIT :limit OFFSET :offset";

        // 키 String, 값에는 어떠한 값도 들어갈 수 있으므로 Object타입
        Map<String, Object> params = new HashMap<>();
        params.put("limit", pageable.getPageSize());
        params.put("offset", pageable.getOffset());

        List<User> users = jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(User.class));

        String countAllDataQuery = "SELECT count(*) FROM users";

        return PageableExecutionUtils.getPage(users, pageable,
                () -> jdbcTemplate.queryForObject(countAllDataQuery, params, Long.class));
        // 페이지를 사용하려면 전체 데이터가 몇건 필요한지?
        // ex. 전체데이터가 100건일떄 한 페이지에서 보여줄 데이터는 20개정도를 위치시키면
        // 5페이지가 필요할 것이다.
        // 따라서 전체 데이터의 건수를 구해야할 것이다.
        // 이 리턴은 Object로 리턴될 것이다.
    }
}
