package springdatajdbc;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>, CustomUserRepository {
    List<User> findByName(String name);
}
