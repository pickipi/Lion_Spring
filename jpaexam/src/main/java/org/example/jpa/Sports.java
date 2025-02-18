package org.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// #. 상속매핑전략 3가지 중 : 2. 조인 테이블 전략
// 모든 속성을 가진 Sports (부모클래스)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter@Setter
public class Sports {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
}

@Entity
@Getter@Setter
class Football extends Sports{
    private String uniformColor; // 유니폼 색깔
}

@Entity
@Getter@Setter
class Basketball extends Sports{
    private int playerNumber; // 농구의 인원 수
}
