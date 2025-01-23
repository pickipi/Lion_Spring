package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController02 {
    @GetMapping("/todo")
    @ResponseBody
    public String todo(){
        return "todoPage";
    }

    @GetMapping("/greeting")
    public ModelAndView greet(@RequestParam(name="name", required = false, defaultValue ="world") String name){
        // name이 없을수도 있으면 false로 지정, name값을 안갖고 들어왔을때는 world라는 기본값을 가지도록 한다.
        // 그 가져온 값을 String name에 넣는다.
        ModelAndView mv = new ModelAndView("greeting"); // 뷰 이름 설정
        mv.addObject("name", name); // "name"이라는 키로 name이라는 값을 넣음
        return mv;
    }

    // 위의 코드는 이 코드와 같다 (둘 중 어느 것을 사용하든지 상관없다 = 스프링 내부적으로는 같은 일을 하기때문)
    @GetMapping("/greeting2")
    public String greet2(@RequestParam(name="name", required = false, defaultValue ="world") String name, Model model){
        model.addAttribute("name", name); // "name"이라는 키로 name이라는 값을 넣음
        return "greeting"; // 뷰의 이름을 반환함
    }
}
