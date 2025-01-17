package sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import sample.bean.Dice;
import sample.bean.Game;
import sample.bean.Player;

import java.util.List;
@PropertySource({"classpath:game.properties"})

public class GameConfig {
    // 1. 생성자를 통한 주입
//    @Bean
//    public Dice dice(){
//        return new Dice(6);
//    }

    // 2. 설정자를 통한 주입
    @Bean
    public Dice dice(@Value("${face}") int face){
        Dice dice = new Dice();
        dice.setFace(face);
        return dice;
    }

//    // 1. 생성자를 통한 주입
//    // 1번 방법
//    @Bean
//    public Player Isak(Dice dice){
//        Player player = new Player(dice);
//        player.setName("ㅡ이삭ㅡ");
//        return player;
//    }

    // 2번 방법
//    @Bean
//    public Player Isak2(){
//        Player player = new Player(dice());
//        player.setName("이삭");
//        return player;
//    }

    @Bean
    public Player Ben(Dice dice){
//        Player player = new Player(dice); // 1. 생성자를 통한 주입
        Player player = new Player();
        player.setDice(dice); // 2. 설정자를 통한 주입
        player.setName("-ㅡ벤ㅡ-");
        return player;
    }

    @Bean
    public Player kim(Dice dice){
//        Player player = new Player(dice); // 1. 생성자를 통한 주입
        Player player = new Player();
        player.setDice(dice); // 2. 설정자를 통한 주입
        player.setName("-ㅡ김ㅡ-");
        return player;
    }

    @Bean
    public Player rock(Dice dice){
//        Player player = new Player(dice); // 1. 생성자를 통한 주입
        Player player = new Player();
        player.setDice(dice); // 2. 설정자를 통한 주입
        player.setName("-ㅡ락ㅡ-");
        return player;
    }

    // 1. 생성자를 통한 주입
//    @Bean
//    public Game game(List<Player> playerList){
//        return new Game(playerList); // 생성자 주입
//    }

    // 2. 설정자를 통한 주입
    @Bean
    public Game game(List<Player> playerList){
        Game game = new Game();
        game.setList(playerList); // 설성자를 통한 주입
        return game;
    }
}
