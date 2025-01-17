package sample.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sample.bean.Book;
import sample.bean.Game;
import sample.bean.MyBean;
import sample.config.GameConfig;

public class SpringExam02 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);
        Game game = context.getBean(Game.class); // 하나 밖에 없으므로 id를 지정하지않음
        game.play();

        Book book = context.getBean(Book.class);

        MyBean myBean = context.getBean(MyBean.class);
        myBean.setName("MYBEAN입니다.");

    }
}
