package com.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 컨트롤러임을 알려주기 // (1. Bean을 등록하기 위해서, 2. 정보들을 등록해주기 위하여)
public class MyController {
    //http://localhost:8080/home?name=willian&age=20&phone=01011111111&address=fulham
    @GetMapping("/home") // URL로 /home을 뒤에 붙여 요청하면 "home"이 리턴될 것, // RestController에서는 화면에 home이라는 텍스트가 나왔었음
    public String home(HttpServletRequest request){
        String name = request.getParameter("name");
        System.out.println(name);
        return "home";
    }

    @GetMapping("/about")
    public String about(@RequestParam("name") String name){
        System.out.println(name);
        return "about";
    }

    @GetMapping("/contact")
    public String contact(@RequestParam("name") String name, Model model){
        System.out.println(name);

        model.addAttribute("name", name); // name이라는 key로 name을 맡김, 이를 templates -> home.html에서 이름을 출력하도록 할 수 있음
        return "contact";
    }
}
