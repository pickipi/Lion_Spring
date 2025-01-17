package sample.bean;

import java.util.List;

public class Game {
    private List<Player> list; // 플레이어 리스트 (스프링 공장을 통해서 생성 및 주입 받음)

    // 2. 설정자를 통한 주입을 위한 기본 생성자
    public Game(){
        System.out.println("설정자를 통한 기본생성자 실행!");
    }

    // 2. 설정자를 통한 주입
    public void setList(List<Player> list) {
        this.list = list;
    }

    // 1. 생성자를 통한 주입
    public Game(List<Player> list) {
        System.out.println("Game(List<Player> list) 생성자 실행!");
        this.list = list;
    }

    // 플레이어 리스트를 순회하여 각 플레이어가 play()할 수 있도록 구현
    public void play(){
        for(Player player : list){
            player.play();
        }
    }

}
