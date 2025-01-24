package com.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {
    @GetMapping("/start")
    public String startProcess(Model model){
        System.out.println("Process Start!!!");
        model.addAttribute("forwardTest", "[Forward] Jonny");
        return "forward:/forward";
    }

    @GetMapping("/forward")
    public String forward(Model model, HttpServletRequest request){
        System.out.println("forward Start!!!");
        System.out.println("forward Test ::::: " + model.getAttribute("forwardTest"));
        System.out.println(request.getAttribute("forwardTest"));
        return "forwardPage";
    }

    @GetMapping("redirect")
    public String redirect(Model model){
        System.out.println("redirect");
        model.addAttribute("redirectTest", "[Redirect] Jonny");
                return "redirect:/finalDestination";
    }

    @GetMapping("/finalDestination")
    public String finalDestination(Model model, HttpServletRequest request){
        System.out.println("redirect Start!!!");
        System.out.println("redirect Test ::::: " + model.getAttribute("redirectTest"));
        System.out.println(request.getAttribute("redirectTest"));

        return "redirectPage";

    }
}
