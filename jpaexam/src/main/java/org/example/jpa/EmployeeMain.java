package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeMain {
    public static void main(String[] args) {
        find();
    }

    // 데이터 조회
    private static void find(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try{
//            // 1) 한 프로젝트에 속한 사원들 출력
//            Project project = em.find(Project.class, 1L);
//            log.info("[SELECT] 프로젝트 이름 : {}", project.getTitle());
//
//            for(Employee employee : project.getEmployees()){
//                log.info("{}의 사원 이름 : {}", project.getTitle(), employee.getName());
//            }

            // 2) 한 사원이 진행한 프로젝트들 출력
            Employee employee = em.find(Employee.class, 1L);
            log.info("[SELECT] 사원 이름 : {}", employee.getName());
            for(Project project2 : employee.getProjects()){
                log.info("{}의 프로젝트명 : {}", employee.getName(), project2.getTitle());
            }
        }finally{
            em.close();
        }
    }
}
