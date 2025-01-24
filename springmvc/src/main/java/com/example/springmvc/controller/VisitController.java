package com.example.springmvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

// 쿠키에 대한 컨트롤러
@Controller
public class VisitController {
    @GetMapping("/visit")
    public String showVisit(
            @CookieValue(name ="lastVisit", defaultValue = "N/A") String lastVisit,
            HttpServletResponse response,
            Model model){

        // lastVisit이름으로 쿠키를 만들고 oreoCookie라는 값을 넣음
        Cookie cookie = new Cookie("lastVisit", "oreoCookie");

        // 이 setDomain()을 생략하면 "/"가 default값, 이렇게 지정하면 내 애플리케이션 전체에서 이 쿠키를 꺼내올 수 있다는 것
//        cookie.setDomain("/");

        // 쿠키의 유지시간을 초 단위 값으로 받으므로 60*60*24이면 하루를 의미 (1 day)
        // 60*60은 1시간을 의미
        cookie.setMaxAge(60*60*24);

        // 이 쿠키정보들은 반드시 응답에 포함시켜야한다.
        response.addCookie(cookie); // response는 addCookie()메소드가 있는 것을 보니 여러개 저장할 수 있고, 쿠키를 얻어올때도 "배열"로 얻어오는 메소드를 가진다.

        model.addAttribute("lastVisit", lastVisit);
        return "visit"; // 뷰는 visit 뷰에서 출력하도록 함
    }
}
