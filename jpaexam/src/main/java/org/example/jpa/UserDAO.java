package org.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserDAO {
    private EntityManagerFactory entityManagerFactory;

    public UserDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("UserPU");
    }

    // User엔티티를 받아서 생성
    public void createUser(User user){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();

            // 엔티티를 영속성 컨텍스트에서 관리할 수 있도록 받아들인 user엔티티를 넣음
            entityManager.persist(user);

            entityManager.getTransaction().commit();
        }finally{
            entityManager.close();
        }
    }

    // 데이터 조회 메소드
    public User findUser(Long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            // find()는 트랜잭션 안에 들어가있지 않아도 동작하므로
            return entityManager.find(User.class, id);
        }finally{
            entityManager.close();
        }
    }

    // 데이터 수정 메소드
    public void updateUser(User user){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        }finally{
            entityManager.close();
        }
    }

    // 데이터 삭제 메소드
    public void deleteUser(User user){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            // <오류발생 수정전>
            //  entityManager.remove(user); // 오류 발생 : 영속성 컨텍스트가 각 메소드마다 서로 다르기때문

            /* <오류발생 해결>
            User user를 받아왔을때 이 user가 존재한다면
            즉 지금 영속성 컨텍스트안에 이미 해당 엔티티가 존재한다면remove하고
            없다면, merge
             */
            entityManager.remove(entityManager.contains(user)?user : entityManager.merge(user));

            entityManager.getTransaction().commit();
        }finally{
            entityManager.close();
        }
    }
}
