package com.example.restexam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/memos")
public class MemoRestController {
    private final Map<Long, String> memos = new HashMap<>();

    private final AtomicLong counter = new AtomicLong();
    // AtomicLong은 id값 처럼 자동으로 증가하는 값을 처리하는데 사용


    // 메모를 생성함
    @PostMapping
    public ResponseEntity<Long> createMemo(@RequestBody String content){
        // Atomic 관련 클래스들은 스레드가 안전할 수 있도록 번호들을 중복되지 않도록
        // 도움을 주는 객체이다.
        Long id = counter.incrementAndGet();

        memos.put(id, content);
        return ResponseEntity.ok(id);
    }

    // 메모를 얻어옴
    @GetMapping("/{id}")
    public ResponseEntity<String> getMemo(@PathVariable Long id){
        String memo = memos.get(id);
        if(memo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(memo);
    }

    // 메모를 수정함
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMemo(@PathVariable Long id, @RequestBody String content){
        if(!memos.containsKey(id)){
            return ResponseEntity.notFound().build();
        }
        memos.put(id, content);
        return ResponseEntity.ok("메모가 성공적으로 업데이트 되었습니다.");
    }

    // 메모를 삭제함
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemo(@PathVariable Long id){
        String removedMemo = memos.remove(id);
        if(removedMemo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("메모를 성공적으로 삭제하였습니다.");
    }
}
