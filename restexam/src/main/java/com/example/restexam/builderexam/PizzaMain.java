package com.example.restexam.builderexam;

public class PizzaMain {
    public static void main(String[] args) {
        // 1. 일반적인 객체 생성
        Pizza pizza = new Pizza("Small", true, true, true);

        // 2. 빌더패턴으로 객체 생성
        Pizza pizza2 = Pizza.builder()
                .size("Medium")
                .cheese(false)
                .onion(false)
                .potato(true)
                .build();

        System.out.println("[일반피자] : " + pizza);
        System.out.println("[빌더패턴 피자] : " + pizza2);
    }
}
