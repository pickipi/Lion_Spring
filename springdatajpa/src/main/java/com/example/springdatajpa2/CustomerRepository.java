package com.example.springdatajpa2;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 이름으로 조회
    List<Customer> findByName(String name);

    // 이메일로 조회
    List<Customer> findByEmail(String email);

    // 이메일에 특정 문자열을 포함하는 고객 조회 (페이지 적용)
    List<Customer> findByEmailContaining(String email, Pageable pageable);

    // 각 고객과 고객의 주문 수를 조회
    @Query("SELECT COUNT(o) FROM Order o WHERE o.customer.id = :id")
    int findOrdersCountByCustomer(@Param("id") Long id);

    // 고객의 세부 정보와 그 고객의 가장 최근 주문 조회
    @Query("SELECT c, o FROM Customer c JOIN c.orders o " +
            "WHERE o.date = (SELECT MAX(o2.date) FROM Order o2 WHERE o2.customer.id = c.id)")
    List<Object[]> findRecentOrder(Long id);

    // 평균 나이보다 많은 고객을 조회
    @Query("SELECT c FROM Customer c WHERE c.age > (SELECT avg(c2.age) FROM Customer c2)")
    List<Customer> findCustomersOlderThanAverage();
}
