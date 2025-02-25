package com.example.restexam.controller;

import com.example.restexam.domain.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api3/memos")
public class MemoRestController3 {
    private final Map<Long, Memo> memos = new HashMap<>();  //왜 만드는지 이해안되시면 질문..
    private final AtomicLong counter = new AtomicLong();

    //curl -X POST -H "Content-type: application/json" -d "{\"content\":\"첫번째 메모\"}"  http://localhost:8080/api3/memos
    //http://localhost:8080/api3/memos      --  POST     저장 (C)
    @PostMapping
    public Long createMemo(@RequestBody Memo memo){
        Long id = counter.incrementAndGet();
        memo.setId(id);
        memos.put(id,memo);
        return id;
    }
    //curl -X GET http://localhost:8080/api3/memos
    //http://localhost:8080/api3/memos      --  GET
    @GetMapping
//    public Map<Long, Memo> getMemos(){
//        return memos;
//    }

    public List<Memo> getMemos(){
        return new ArrayList<>(memos.values());
    }

    //curl -X GET http://localhost:8080/api3/memos/1
    //http://localhost:8080/api3/memos/1    --  GET
    @GetMapping("/{id}")
    public Memo getMemo(@PathVariable("id") Long id){

        return memos.getOrDefault(id,null);
    }

    //curl -X PUT -H "Content-type: application/json" -d "{\"content\":\"수정된 메모\"}" http://localhost:8080/api3/memos/1
    //http://localhost:8080/api3/memos/1    --  put
    @PutMapping("/{id}")
    public String updateMemo(@PathVariable("id")Long id, @RequestBody Memo memo){
        if(!memos.containsKey(id)){
            return "해당 메모를 찾을 수 없습니다!";
        }
        memo.setId(id);
        memos.put(id,memo);
        return "메모 수정에 성공하였습니다!";
    }
    //curl -X DELETE http://localhost:8080/api3/memos/1
    //http://localhost:8080/api3/memos/1    --  DELETE
    @DeleteMapping("/{id}")
    public String deleteMemo(@PathVariable("id")Long id){
        if(memos.remove(id) == null){
            return "해당 메모를 찾을 수 없습니다!";
        }
        return "메모 삭제에 성공하였습니다!";
    }
}