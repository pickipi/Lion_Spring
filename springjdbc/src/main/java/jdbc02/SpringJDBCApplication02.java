package jdbc02;

import jdbc02.dao.Dept;
import jdbc02.dao.DeptDao;
import jdbc02.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringJDBCApplication02 implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringJDBCApplication02.class);
    }

    @Autowired
    private UserDao userDao;

    @Autowired // 꼭 각 필드마다 어노테이션을 붙여주어야한다.
    private DeptDao deptDao;

    @Override
    public void run(String... args) throws Exception {
        // users 테이블 실습
        //insert test
//        userDao.insertUser(new User(null, "Dango", "Watara@premier.com"));
//        userDao.insertUser(new User(null, "Enes", "Unal@premier.com"));
//        userDao.insertUser(new User(null, "Sepp", "Vandenberg@premier.com"));
//        userDao.insertUser(new User(null, "Vincent", "Kompany@premier.com"));
//        userDao.insertUser(new User(null, "Baines", "Baines@premier.com"));

        //update test
//        userDao.updateUserEmail("Baines", "Everton@premier.com");

        // delete test
//        userDao.deleteUser("Watkins");

        //select test
//        List<User> users = userDao.findAllUsers();
//        for(User user : users){
//            System.out.println(user);
//        }

        // dept 테이블 실습
        //INSERT
//        deptDao.insertDept(new Dept(null, "FC Benfica", "Portugal"));
//        deptDao.insertDept(new Dept(null, "FC Dortmund", "Germany"));
//        deptDao.insertDept(new Dept(null, "Deportivo", "Spain"));

        //UPDATE
//        deptDao.updateDept(new Dept(1L, "update1", "updateTest"));

        //DELETE
//        deptDao.deleteDept(1L);
//
        //SELECT
        List<Dept> datas = deptDao.findAllDept();
        for(Dept data : datas){
            System.out.println(data);
        }
    }
}
