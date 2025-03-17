package com.example.swaggerexam.tdd;

public class CalMain {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.add(3, 4);
        if(result == 7){
            System.out.println("성공");
        }
    }
}
