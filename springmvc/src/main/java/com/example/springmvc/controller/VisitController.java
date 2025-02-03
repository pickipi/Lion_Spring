package com.example.springmvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

// 쿠키에 대한 컨트롤러
@Controller
public class VisitController {
    @GetMapping("/cookieSetForm")
    public String cookieSetForm(){
        // 해당 폼이 요청이 되면, 쿠키 이름과 쿠키 값을 받는 화면을 만듦
        return "cookieset";
    }

    @GetMapping("/cookieSet")
    public String cookieSet(@RequestParam(name = "cookieName") String cookieName,
                            @RequestParam(name = "cookieValue") String cookieValue,
                            HttpServletResponse response){
        // 이 요청에서는 쿠키 이름과 쿠키 값을 받아서 쿠키를 저장하는 부분
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(7*24*60*60); // 7일 유지
        response.addCookie(cookie); // 반드시 addCookie해주어야 "응답에 쿠키 추가"

        // 쿠키가 저장되면 /cookieView로 리다이렉트되도록 구현
        return "redirect:/cookieView";
    }

    @GetMapping("/cookieView")
    public String cookieView(HttpServletRequest request, Model model){
        //이 요청에서는 모든 쿠키를 보여주는 화면을 만들어 주세요.
        Cookie[] cookies = request.getCookies();
        List<String> cookieList = new ArrayList<>();

        if(cookies != null){
            for (Cookie cookie : cookies){
                System.out.print(cookie.getName() +":::");
                System.out.println(cookie.getValue());
                cookieList.add(cookie.getName()+"="+cookie.getValue());
            }

            model.addAttribute("cookies",cookieList);

        }
        return "cookieview";
    }

    // 쿠키 삭제
    @GetMapping("/cookieDelete")
    public String cookieDelete(@RequestParam(name="cookieName") String cookieName,
                               HttpServletResponse response){

        Cookie cookie = new Cookie(cookieName, ""); // Value값을 주지 않음으로 delete기능 구현
        cookie.setPath("/");
        cookie.setMaxAge(0); // 생명주기를 0으로 줌으로써 delete기능 구현
        response.addCookie(cookie);
        return "redirect:/cookieView";
    }

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
