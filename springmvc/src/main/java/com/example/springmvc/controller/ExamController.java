package com.example.springmvc.controller;

import com.example.springmvc.domain.User;
import com.example.springmvc.domain.UserForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.*;
import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/list")
    public String showList(Model model){
        List<String> items = Arrays.asList(
                "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
                "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"
                );

        model.addAttribute("itemList", items);

        return "list";
    }

    // 실습. LocalDate 클래스 다루기
    @GetMapping("/datetime")
    public String showDatetime(Model model){
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("currentDateTime", LocalDateTime.now());
        model.addAttribute("currentTime", LocalTime.now());
        model.addAttribute("currentZonedDateTime", ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        return "datetime";
    }

    // 실습. 포워딩으로 (유저이름, 비밀번호) 로그인 페이지 만들기
    @GetMapping("/form")
    public String showForm(Model model){
        model.addAttribute("userForm", new UserForm());
        return "form";
    }

    @PostMapping("/submitForm")
    public String submitForm(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result){
        if(result.hasErrors()){ // 만약 result가 에러를 가졌으면 다시 "form"으로
            return "form"; // 유효성 검사 실패 시 다시 Form View로 리턴하는 것
        }
        return "result"; // 유효성 검사 성공 시 "결과 페이지"로 리다이렉트
    }

    // 실습. 리다이렉트로 (이름, 이메일) 로그인 페이지 만들기
    @GetMapping("/registerForm")
    public String showRegisterForm(Model model){
        model.addAttribute("userRegisterForm", new User());
        return "registerForm";
    }
    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "registerForm";
        }
        return "redirect:/result";
    }




}
