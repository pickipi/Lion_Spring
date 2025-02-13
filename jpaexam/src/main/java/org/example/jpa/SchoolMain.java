package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchoolMain {
    // 데이터 조회
    private static void find(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try{
            School school = em.find(School.class, 1L);
            String findSchool = school.getName();
            log.info("ID [1] School Name : {}", findSchool);

            for(Student student : school.getStudents()){
                log.info("{}'s Student Name : {}", findSchool, student.getName());
            }
        }finally{
            em.close();
        }
    }

    // 데이터 생성
    private static void create(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try{
            // School 생성
            School school = new School("Tiger School");

            // Student 생성
            school.getStudents().add(new Student("Matz", school));
            school.getStudents().add(new Student("Alex", school));

            em.persist(school); // 생성한 school을 영속상태로 만들어줌

            em.getTransaction().commit(); // 모든 변경사항 적용
            String createSchool = school.getName();
            log.info("[NEW] School Name : {}", createSchool);

            for(Student student : school.getStudents()){
                log.info("{}'s Student Name : {}", createSchool, student.getName());
            }
        }finally{
            em.close();
        }
    }

    // 데이터 수정
    private static void update(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try{
            // School 변경
            School school = em.find(School.class, 4L);
            String beforeSchool = school.getName();
            school.setName("Jaguar School"); // Tiger School 변경 -> Jaguar School 변경

            em.getTransaction().commit(); // 모든 변경사항 적용

            String updatedSchool = school.getName();
            log.info("[UPDATE] School Name : {} -> {}", beforeSchool, updatedSchool);

            for(Student student : school.getStudents()){
                log.info("{}'s Student Name : {}", updatedSchool, student.getName());
            }
        }finally{
            em.close();
        }
    }

    // 데이터 삭제
    private static void delete(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try{
            // 이전 정보를 출력하기위한 find()
            School school = em.find(School.class, 3L);
            String deletedSchool = school.getName();

            // School 삭제
            em.remove(school);
            em.getTransaction().commit(); // 모든 변경사항 적용

            log.info("[DELETE] School Name : {} -> [삭제완료]", deletedSchool);
        }finally{
            em.close();
        }
    }

    public static void main(String[] args) {
//        find();
//        create();
//        update();
        delete();
    }
}
