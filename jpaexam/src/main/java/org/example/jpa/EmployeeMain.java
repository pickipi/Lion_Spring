package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeMain {
    public static void main(String[] args) {
//        find();
//        create();
//        update();
        delete();
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

    // 데이터 생성
    private static void create(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Employee employee = new Employee("Neco");
            employee.setName("Williams");

            Project project = new Project();
            project.setTitle("Rightback's Defense Skills");
            employee.getProjects().add(project); // 각각 넣어줌
            project.getEmployees().add(employee); // 각각 넣어줌

            em.persist(employee); // 각각 영속상태로 바꿔줌
            em.persist(project); // 각각 영속상태로 바꿔줌
            em.getTransaction().commit();
        }catch(Exception e){ // 예외발생시
            if(em.getTransaction().isActive()){ // 트랜잭션이 활동 중(=활성화중)이면
                em.getTransaction().rollback(); // 롤백시켜주라는 명령
            }
            throw e; // 호출한 곳에서 예외를 던질 수 있도록 함
        }
        finally{
            em.close();
        }
    }

    // 데이터 수정
    private static void update(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Employee employee = em.find(Employee.class, 1L);
            String beforeEmpName = employee.getName();

            employee.setName("Sven");

            // Project의 3번 id에 변경된 이름의 사원을 추가
            employee.getProjects().add(em.find(Project.class, 3L));

            log.info("[UPDATE] 사원 이름 변경 : {} -> {}", beforeEmpName, employee.getName());
            em.getTransaction().commit();
        }catch(Exception e){ // 예외발생시
            if(em.getTransaction().isActive()){ // 트랜잭션이 활동 중(=활성화중)이면
                em.getTransaction().rollback(); // 롤백시켜주라는 명령
            }
            throw e; // 호출한 곳에서 예외를 던질 수 있도록 함
        }
        finally{
            em.close();
        }
    }

    // 데이터 삭제
    private static void delete(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Employee employee = em.find(Employee.class, 1L);
            em.remove(employee); // 1번 사원을 삭제할때, 관계테이블(employees_projects 테이블의 employee_id = 1에 해당하는 값도 삭제되어야한다)
            em.getTransaction().commit();
        }catch(Exception e){ // 예외발생시
            if(em.getTransaction().isActive()){ // 트랜잭션이 활동 중(=활성화중)이면
                em.getTransaction().rollback(); // 롤백시켜주라는 명령
            }
            throw e; // 호출한 곳에서 예외를 던질 수 있도록 함
        }
        finally{
            em.close();
        }
    }
}
