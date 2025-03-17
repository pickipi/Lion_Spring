package com.example.swaggerexam.tdd;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(7, cal.add(3, 4));
        assertEquals(15, cal.add(10, 5));
        assertEquals(-1, cal.add(2, -3));
    }

    @Test
    @DisplayName("나눗셈 테스트")
    void divide(){
        System.out.println("[divide] test 메서드 실행 ");
        assertEquals(0.0, cal.divide(0, 3));
        assertEquals(2.0, cal.divide(6, 3));
        assertEquals(4.0, cal.divide(20, 5));
        assertEquals(-4.0, cal.divide(20, -5));
    }

    @Test
    @DisplayName("0으로 나눈 예외발생 테스트")
    void divideByZero(){
//        assertEquals(0.0, cal.divide(20, 0)); // 예외발생 테스트케이스
        assertThrows(ArithmeticException.class, () -> cal.divide(6, 0));
        // 이 코드는 6, 0 테스트 값을 입력했을때 ArithmeticException이 발생해야 성공이라는 의미
    }

    @Test
    @DisplayName("곱셈 테스트")
    void multiply(){
        assertEquals(12, cal.multiply(6, 2));
        assertEquals(0, cal.multiply(6, 0));
    }

    @Test
    @DisplayName("뺄셈 테스트")
    void subtract(){
        assertEquals(15, cal.subtract(6, -9));
        assertEquals(25, cal.subtract(10, -15));
        assertEquals(-13, cal.subtract(-10, 3));
    }
}