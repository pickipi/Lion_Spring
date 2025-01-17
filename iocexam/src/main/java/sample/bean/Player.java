package sample.bean;

public class Player {
    private String name;
    private Dice dice; // 선언만 한 경우이므로 초기화 부분은 실행될때 주사위를 주입받을 것

    // 2. 설정자를 통한 주입
    public Player() {
    }

    // 1. 생성자를 통한 주입
//    public Player(Dice dice) {
//        this.dice = dice;
//    }


    // 2. 설정자를 통한 주입
    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 주사위 게임을 하는 메소드
    public void play(){
        System.out.println(name + "은 주사위를 던져서 [" + dice.getNumber() + "]가 나왔습니다.");
    }
}
