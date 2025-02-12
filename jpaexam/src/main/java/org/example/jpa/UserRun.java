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
    }
}
