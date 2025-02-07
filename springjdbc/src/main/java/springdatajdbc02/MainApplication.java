package springdatajdbc02;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    public CommandLineRunner demo(UserService userService){
        return args -> {
            //userDao.createAndUpdateUser("Dominic", "Solanke@premier.com", "error");

            userService.createAndUpdateUser("Owen", "Beck@premier.com", "error");
        };
    }
}

