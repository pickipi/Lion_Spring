package com.example.springdatajpa3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findUsersByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        // query.select(user).where(cb.equal(user.get("name"), name));
        query.select(user).where(cb.like(user.get("name"), "%"+name+"%"));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<User> findUsersDynamically(String name, String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class); // createQuery는 대상이 될 엔티티를 넣어줌 (User.class)
        Root<User> user = query.from(User.class);

        // 조건이 들어갈 부분
        // 조건을 List에 담는데 Predicate는 조건을 리스트에 담도록 할 수 있음
        // 조건이 여러개 있을 수 있으므로 리스트에 담음
        // 이 Predicate는 Lambda에서 조건에 따라서 boolean을 리턴하는 인터페이스임
        // 조건식에 따라 isNegated 등에 대한 결과를 반환함
        // 이는 BooleanOperator에서 AND, OR등을 자동으로 붙여서 쿼리를 만들어주는 역할을 함
        // 조건이 여러개 있을 수 있으므로 Predicate는 이 여러 조건들을 저장할 목적으로 사용될뿐
        // 조건 배열에는 u.name = name, u.email = email 처럼 저장되어 있을 것
        List<Predicate> predicates = new ArrayList<>();
        if(name != null){
            predicates.add(cb.equal(user.get("name"), name)); // predicates는 List이므로 List의 add()메소드로 쿼리문을 인자로 받음
        }
        if(email != null){
            predicates.add(cb.equal(user.get("email"), email));
        }

        // predicates리스트의 toArray를 통해 배열로 꺼내와서 0번째 인덱스로 만들어줄 것
        // 이 배열에는 cb.equals(user.get("name"), name)과 cb.equals(user.get("email"), email) 두개가 저장되어있을 것
        // 그것을 and() 를 통해 AND 조건으로 붙어서 수행이 될 것 (여러 조건들을 AND, AND, AND를 통해 붙여주겠다는 것)
        // 만약 and()가 아닌 or()이면 OR 조건으로 toArray() 에 저장된 여러 조건들을 OR, OR, OR를 통해 붙여주겠다는 것
        query.select(user).where(cb.and(predicates.toArray(new Predicate[0])));

        // 엔티티매니저로부터 쿼리를 생성하고 그 쿼리로는 지금까지 만든 query를 넣어준 후 결과 리스트로 반환하는 형태
        return entityManager.createQuery(query).getResultList();
    }
}
