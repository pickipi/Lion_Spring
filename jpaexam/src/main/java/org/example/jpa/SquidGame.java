package org.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// #. 상속매핑전략 3가지 중 : 3. 테이블 당 구체 클래스 전략
// 모든 속성을 가진 Sports (부모클래스)
@Entity
@Getter@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SquidGame {
    @Id@GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private int survivor; // 모든 게임의 공통된 생존자
}

@Entity
@Getter@Setter
class Dalgona extends SquidGame{
    private String shape; // 달고나 게임의 달고나 모양
}

@Entity
@Getter@Setter
class Gonggi extends SquidGame{
    private int passMinNumber; // 공기놀이 게임의 잡은 돌의 최소 통과 기준
}
