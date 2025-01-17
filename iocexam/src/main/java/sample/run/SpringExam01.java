package sample.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sample.bean.Book;
import sample.bean.MyBean;
import sample.config.MyBeanConfig;

public class SpringExam01 {
    public static void main(String[] args) {
        // 1. 직접 객체를 생성하는 경우라면
        MyBean bean = new MyBean();
        bean.setName("Bruno");
        System.out.println(bean.getName());

        // 2-2. Java Config를 통해 Bean 등록
        System.out.println("ApplicationContext 생성 전 ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfig.class); // 이 공장은 어떤것을 참조해야할까
        System.out.println("ApplicationContext 생성 후 ---");

        MyBean bean1 = (MyBean) context.getBean("myBean"); // LookUp방식
        bean1.setName("Ditt");
        System.out.println(bean1);

        MyBean bean2 = context.getBean("myBean", MyBean.class);
        System.out.println(bean2);

        if(bean1 == bean2) System.out.println("같은 인스턴스");
        else System.out.println("다른 인스턴스");

        // id없이 타입만 가지고 스프링이 매핑(=LookUp)해줌
        Book book = context.getBean(Book.class);
        System.out.println(book);

        // 초기값을 가지고 생성되는 생성자를 get하여 bean3에 넣어줌
        // getBean() 하는 시점에 객체가 생성되는 시점을 가짐
        MyBean bean3 = context.getBean("myBean3", MyBean.class);
        System.out.println(bean3);
    }
}
