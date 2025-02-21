package com.example.restexam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {
    @GetMapping("/example")
    public String getExample(){
        return "Hello!!!";
    }

    @GetMapping("/example/entity")
    public ResponseEntity<String> getResEntity(){
        return ResponseEntity.status(HttpStatus.OK).header("Custom-Header", "Kaide").body("[Body] 부분입니다.");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<oldUser> getUserById(@PathVariable("id")Long id){
        oldUser user = new oldUser(id, "Tyler", "010-1111-1111", "Cheshire");

        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
