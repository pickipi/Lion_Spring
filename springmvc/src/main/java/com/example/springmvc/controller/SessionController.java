package com.example.springmvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/session")
public class SessionController {
    @GetMapping("/visit") // @RequestMapping 설정으로 visit URI사용 가능
    public String visit(HttpSession session, Model model){
        //세션에서 방문 횟수 가져오기

        // visitCount는 int형일테니 Integer로 얻어온다 (getAttribute의 메소드는 Object로 리턴되기때문)
        Integer visitCount = (Integer)session.getAttribute("visitCount");

        if(visitCount == null){
            visitCount = 0;
        }
        visitCount++;

        session.setAttribute("visitCount", visitCount);
        model.addAttribute("visitCount", visitCount);
        return "visitSession";
    }
}
