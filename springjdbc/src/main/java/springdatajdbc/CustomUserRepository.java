package springdatajdbc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {
    Page<User> findAllUsersWithPagination(Pageable pageable);
    // 페이지에 있는 정보를 기준으로 찾아준다.
}
