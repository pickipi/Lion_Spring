package org.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UserRun {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("UserPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println(entityManager);


        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // <<INSERT>> : 데이터 삽입
        // 엔티티
//        User user = new User();
////        user.setId(2L);
//        user.setName("sample");
//        user.setEmail("sample@exam.com");
//        // 엔티티가 생성 - 아직 영속성 컨텍스트와는 관계없는 상태 (비영속상태)
//
//        entityManager.persist(user);

        // <<SELECT>> : 데이터 조회
        User user = entityManager.find(User.class, 2L);
        User user2 = entityManager.find(User.class, 2L);

        if(user == user2){
            System.out.println("user == user2 >> user와 user2는 같은 객체입니다.");
        }else{
            System.out.println("user != user2 >> user와 user2는 다른 객체입니다.");
        }

        System.out.println(user);

        // <<UPDATE>> : 데이터 수정
//        User user3 = entityManager.find(User.class, 1L);
//        user3.setName("newSample"); // DB에 바로 접근해서 수행하지 않음
//        // = 트랜잭션이 종료될때까지는 영속성 컨텍스트에서 관리하는 것
//
//        // 중간에 다른 일 수행
//        User user4 = entityManager.find(User.class, 1L);
//        user4.setName("sample");
//
//        System.out.println("트랜잭션.commit() 실행전");
//        transaction.commit(); // 영속성 컨텍스트를 분석해서 알맞는 동작을 수행
//        System.out.println("트랜잭션.commit() 실행후" );

        // <<DELETE>> : 데이터 삭제
        User user5 = entityManager.find(User.class, 1L);
        entityManager.remove(user5);

        System.out.println("트랜잭션.commit() 실행전");
        transaction.commit(); // 영속성 컨텍스트를 분석해서 알맞는 동작을 수행
        System.out.println("트랜잭션.commit() 실행후" );
    }
}
