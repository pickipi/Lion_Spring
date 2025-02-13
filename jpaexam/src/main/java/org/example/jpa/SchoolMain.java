package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchoolMain {
    // 데이터 조회
    private static void find(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try{
            School school = entityManager.find(School.class, 1L);
            String findSchool = school.getName();
            log.info("ID [1] School Name : {}", findSchool);

            for(Student student : school.getStudents()){
                log.info("{}'s Student Name : {}", findSchool, student.getName());
            }
        }finally{
            entityManager.close();
        }
    }


    public static void main(String[] args) {
        find();
    }
}
