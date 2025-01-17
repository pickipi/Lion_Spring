package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import sample.bean.Book;
import sample.bean.MyBean;

public class MyBeanConfig {
    // 스프링 공장에게 어떤 빈을 관리할지 알려줌
    @Bean
    public MyBean myBean(){
        return new MyBean();
    }

    @Bean
    public Book book(){
        return new Book();
    }

    // 초기값을 가지고 생성되는 생성자 사용
    @Bean
    public MyBean myBean3(){
        return new MyBean("초기이름", 30);
    }

    @Bean
    @Scope("prototype")
    public MyBean myBean2(){
        return new MyBean();
    }

}
