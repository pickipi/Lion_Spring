package com.example.restexam.controller;

import com.example.restexam.domain.Todo;
import com.example.restexam.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // to-do 얻기
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        return ResponseEntity.ok(todoService.getTodos());
    }

    // to-do 추가
    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo){
        Todo createTodo = todoService.addTodo(todo.getTodo());
        // 상태코드에는 CREATED를 넘기고, body부분에는 만들어놓은 to-do(createTodo)를 넘긴다.
        return ResponseEntity.status(HttpStatus.CREATED).body(createTodo);
    }

    // to-do 수정 (@PatchMapping, @PutMapping 사용 가능) - done (완료/미완료) 여부를 수정함
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id")Long id){
        Todo updateTodo = todoService.updateTodo(id);
        return ResponseEntity.ok(updateTodo);
    }

    // to-do 삭제 (@DeleteMapping 사용) - Rest API 고려 X (@RequestBody 사용)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@RequestBody Todo todo){
        todoService.deleteTodo(todo.getId());
        return ResponseEntity.noContent().build();// <Void>로 아무것도 반환하지 않으므로 noContent()를 사용
    }
}
