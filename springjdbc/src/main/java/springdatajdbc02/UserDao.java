package springdatajdbc02;

public interface UserDao {
    void createAndUpdateUser(String name, String email, String newEmail);
    void addUser(String name, String email);
    void updateUser(String name, String newEmail);
}
