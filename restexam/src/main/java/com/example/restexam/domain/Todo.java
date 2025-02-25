package com.example.restexam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "todos")
@Getter@Setter@NoArgsConstructor
@ToString
public class Todo {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String todo; // 할일 내용
    private boolean done; // 완료 여부

    // 할일을 가지는 생성자 만들기 (생성자가 하나라도 만들어지면 기본생성자 (NoArgsConstructor)는 반드시 추가해주어야함)
    public Todo(String todo) {
        this.todo = todo;
    }
}
