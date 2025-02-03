package com.example.springmvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/session")
@SessionAttributes("visitCount")
public class SessionController {
    // 세션 접근 방법 첫번쨰. HttpSession에 직접 접근하는 방법
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

    // 세션 접근 방법 두번째. 스프링에서 제공하는 기능 사용
    // =HttpSession을 직접 접근하지 않고 스프링을 이용하는 방식
    // Scope(범위)가 Request이다.
    @ModelAttribute("visitCount")
    public Integer initVisitCount(){
        return 0;
    }

    @GetMapping("/visit2")
    public String visit(@ModelAttribute("visitCount") Integer visitCount, Model model){
        visitCount++;
        model.addAttribute("visitCount", visitCount);
        return "visitSession";
    }

    // 세션 초기화
    @GetMapping("/resetVisit")
    public String resetVisit(SessionStatus status){
        status.setComplete();
        return "redirect:/session/visit2";
    }
}
