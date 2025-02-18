package org.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// #. 상속매핑전략 3가지 중 : 1. 단일 테이블 전략
// 모든 속성을 가진 Vehicle (부모클래스)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter@Setter
public abstract class Vehicle {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    private String manufacturer; // 제조회사
}

// Vehicle을 상속받는 클래스들
@Entity
@Getter@Setter
@DiscriminatorValue("CAR")
class Car extends Vehicle{
    private int seatCount; // 의자의 개수 컬럼
}

@Entity
@Getter@Setter
@DiscriminatorValue("BUS")
class Bus extends Vehicle{
    private String busColor; // 버스의 색깔 컬럼
}

@Entity
@Getter@Setter
@DiscriminatorValue("TRUCK")
class Truck extends Vehicle{
    private int payloadCapacity; // 적재량 컬럼
}

@Entity
@Getter@Setter
@DiscriminatorValue("TAXI")
class Taxi extends Vehicle{
    private String passengerGender; // 택시 승객 성별
}


