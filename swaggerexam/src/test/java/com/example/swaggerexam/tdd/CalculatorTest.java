package com.example.swaggerexam.tdd;

import org.junit.jupiter.api.*;

class CalculatorTest {
    Calculator cal;

    @BeforeAll
    static void beforeAll(){
        System.out.println("-----모든 테스트 [실행 전]에 한번 실행-----");
    }

    @AfterAll
    static void AfterAll(){
        System.out.println("-----모든 테스트 [실행 후]에 한번 실행-----");
    }

    @BeforeEach
    void setUp() {
        System.out.println("각 테스트 메서드가 실행되기 전에 실행.");
        cal = new Calculator(); // 테스트 메소드 실행 전 객체 생성
    }
    @AfterEach
    void tearDown() {
        System.out.println("각 테스트 메서드가 실행된 후 실행.");
        cal = null; // 테스트 메소드 실행 후에는 다시 객체를 null로 변경
    }
    @Test
    void add() {
        System.out.println("add test 메서드 실행 ");
    }
}