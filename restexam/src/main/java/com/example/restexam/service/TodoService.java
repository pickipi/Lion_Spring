package com.example.restexam.service;

import com.example.restexam.domain.Todo;
import com.example.restexam.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 4개의 비즈니스 만들기

    // 1. 전체 할 일 조회
    @Transactional(readOnly = true)
    public List<Todo> getTodos(){
        // by에는 정렬할 컬럼을 넣기
        // .descending() : 내림차순 정렬로 설정
        return todoRepository.findAll(Sort.by("id").descending());
    }

    // 2. 할 일 추가
    @Transactional
    public Todo addTodo(String todo){
        return todoRepository.save(new Todo(todo));
    }

    // 3. 할 일 완료, 미완료 변경
    @Transactional
    public Todo updateTodo(Long id){
        boolean exists = todoRepository.existsById(id);
        if(!exists){
            throw new EntityNotFoundException("이미 삭제된 Todo입니다." + id);
        }

        Todo todo = todoRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("id에 해당되는 todo를 찾을 수 없습니다" + id));
        todo.setDone(!todo.isDone()); // 반대로 바꿔줄 수 있도록 isDone()을 불러와서 (!)로써 변경해준다.

        return todo;
    }

    // 4. 할 일 삭제
    @Transactional
    public void deleteTodo(Long id){
        // 존재하지 않을 경우에는 예외발생
        if(!todoRepository.existsById(id)){
            throw new RuntimeException("id에 해당하는 todo가 없습니다." + id);
        }
        // 존재할 경우 삭제
        todoRepository.deleteById(id);
    }
}
