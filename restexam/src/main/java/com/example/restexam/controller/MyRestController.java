package com.example.restexam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MyRestController {
    @GetMapping("/api/greeting")
    public Map<String, String> greet(@RequestParam(name = "message", required = false, defaultValue = "hello")String message){
        Map<String, String> res = new HashMap<>();
        res.put("message", message);
        res.put("key", "[key]에 따른 VALUE!");
        res.put("Cookie", "오레오!");
        return res;
    }

    @GetMapping(value = "/api/user", produces = "application/xml")
    public oldUser getUser(){
        return new oldUser("Isak", "010-1111-1111", "타인위어");
    }

    @GetMapping("/api/user/param")
    public oldUser getUserParam(@RequestParam(name="name") String name){
        return new oldUser(name, "010-1111-1111", "타인위어");
    }

    @GetMapping("/api/users")
    public List<oldUser> getUsers(){
        List<oldUser> users = new ArrayList<>();
        users.add(new oldUser("Kaide", "010-2222-2222", "Cheshier"));
        users.add(new oldUser("Anthony", "010-3333-3333", "Cheshier"));
        users.add(new oldUser("Tyler", "010-4444-4444", "Cheshier"));
        return users;
    }
}
