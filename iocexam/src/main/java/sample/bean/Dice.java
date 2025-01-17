package sample.bean;

import org.springframework.stereotype.Component;

@Component
public class Dice {
    private int face;

    // 2. 설정자 사용 방법
    public Dice() {
        System.out.println("Dice() 실행");
    }

    // 1. 컴포넌트 스캔에 필요한 생성자
    public Dice(int face) {
        this.face = face;
        System.out.println("Dice(int) 실행");
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getNumber(){
        return (int)(Math.random() * face) + 1;
    }
}
