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
            School school = new School("Lion School");

            // Student 생성
            Student student1 = new Student("Morgan", school);
            school.getStudents().add(student1); // school의 빈 리스트에 학생 넣기

            Student student2 = new Student("Conor", school);
            school.getStudents().add(student2); // school의 빈 리스트에 학생 넣기

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

    public static void main(String[] args) {
        find();
        create();
    }
}
