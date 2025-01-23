package com.example.springmvc.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {
    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("welcomeMessage", "Welcome to our awesome website!");
        // 키 값으로 welcomeMessage를 가지고 그 값으로 attributeValue에 위치한 문자열을 가져온다.

        List<Item> items = Arrays.asList( // 객체를 생성해서 바로 값을 간단하게 넣어서 사용할때 활용됨, 넣어준 값을 통해 리스트를 새로 생성해주는 기능을 갖고 있는 것
                // Arrays가 갖고 있는 asList()라는 기능
                new Item("Apple", 1.25),
                new Item("Banana", 0.75),
                new Item("Orange", 0.50)
        );

        /*
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Apple", 1.25));
        itemList.add(new Item("Banana", 0.75));
        itemList.add(new Item("Orange", 0.75));

        // 문자열 리스트를 바로 만들어내는 기능
        // List<String> testList = Arrays.asList("a", "b", "c");
        */
        model.addAttribute("items", items);
        return "welcome"; // @GetMapping을 통해 /welcome URL 요청을 받으면 정보 처리 후 welcome.html을 찾도록 return함
    }

    @Getter
    @AllArgsConstructor // 모든 속성의 생성자를 만든다. (public Item(String name, double price) {this.name = name; this.price =price;} 와 동일
    static class Item{
        private String name;
        private double price;
    }
}


