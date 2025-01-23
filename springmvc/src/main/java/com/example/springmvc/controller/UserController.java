package com.example.springmvc.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    @GetMapping("/users")
    public String showUsers(Model model){
        List<User> users = Arrays.asList(
                new User(101, "Duran", false, 21),
                new User(102, "Kubo", false, 23),
                new User(103, "Kerkez", true, 21),
                new User(104, "Zubimendi", false, 25)
        );
        model.addAttribute("DocsTitle", "LEFT 회사 사원 목록표");
        model.addAttribute("userList", users);

        return "userPage";
    }

    @Getter
    @AllArgsConstructor
    class User{
        private int empId;
        private String empName;
        private boolean isAdmin;
        private int age;
    }
}
