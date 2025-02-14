package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonMain {
    public static void main(String[] args) {
//        find();
        create();
    }

    private static void find(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try{
            // 1. 여권으로 데이터 조회
            Passport passport = em.find(Passport.class, 1L);
            log.info("여권 번호 : {}", passport.getPassportNumber());
            log.info("여권 주인 : {}", passport.getPerson().getName());
            log.info("------------------------------------------------");
            // 2. 여권 주인으로 데이터 조회
            Person person = em.find(Person.class, 1L);
            log.info("여권 주인 : {}", passport.getPerson().getName());
            log.info("여권 번호 : {}", passport.getPassportNumber());
        }finally{
            em.close();
        }
    }

    private static void create(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Person person = new Person("Theo");
            Passport passport = new Passport("Z5091312");

            // 연관 관계 설정
            person.setPassport(passport);
            passport.setPerson(person);

            em.persist(person); // passport가 담긴 person 엔티티를 영속화시킴
            em.getTransaction().commit();
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("에러발생!", e);
        }finally{
            em.close();
        }
    }
}
