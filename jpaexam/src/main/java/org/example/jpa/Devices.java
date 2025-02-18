package org.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter@Setter
// 1. 단일 테이블 전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
//@Table(name = "DEVICES_SINGLE")

// 2. 조인 테이블 전략
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "DEVICES_JOINED")
public abstract class Devices {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private int price;
}

//@Table(name = "PHONE_SINGLE")
@Entity
@Getter@Setter
@Table(name = "PHONE_JOIN")
@DiscriminatorValue("PHONE")
class Phone extends Devices{
    private String operatingSystem;
    private int batteryLife;
}

//@Table(name = "LAPTOP_SINGLE")
@Entity
@Getter@Setter
@Table(name = "LAPTOP_JOIN")
@DiscriminatorValue("LAPTOP")
class Laptop extends Devices{
    private int ramSize;
    private String hasTouchScreen;
}

@Slf4j
class DevicesMain{
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Phone phone = new Phone();
            phone.setBrand("Apple");
            phone.setPrice(1300000);
            phone.setBatteryLife(90);
            phone.setOperatingSystem("iOS");

            Laptop laptop = new Laptop();
            laptop.setBrand("Asus");
            laptop.setPrice(750000);
            laptop.setRamSize(16);
            laptop.setHasTouchScreen("있음");

            em.persist(phone);
            em.persist(laptop);
            em.getTransaction().commit();

            // 데이터 조회
            Devices device = em.find(Devices.class, 2L);
            if(device instanceof Phone){
                Phone findPhone = (Phone)device;
                log.info("기기 종류 : [PHONE]");
                log.info("브랜드 : " + findPhone.getBrand());
                log.info("가격 : " + findPhone.getPrice() + "원");
                log.info("배터리 성능 상태 : [" + findPhone.getBatteryLife() + "%]");
                log.info("기기의 OS : [" + findPhone.getOperatingSystem() + "]");
            }else if(device instanceof Laptop){
                Laptop findLaptop = (Laptop)device;
                log.info("기기 종류 : [LAPTOP]");
                log.info("브랜드 : " + findLaptop.getBrand());
                log.info("가격 : " + findLaptop.getPrice() + "원");
                log.info("램 용량 : [" + findLaptop.getRamSize() + "GB]");
                log.info("터치스크린 가능 여부 : [" + findLaptop.getHasTouchScreen() + "]");
            }
        }finally{
            em.close();
        }
    }
}
