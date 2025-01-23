package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam")
public class ExamController {
    @GetMapping("/example")
    public String showExample(Model model){
        model.addAttribute("username", "JuunB");
        model.addAttribute("isAdmin", true);
        model.addAttribute("languages", new String[]{"English", "Spanish", "Portugues"});
        return "example";
    }
}
