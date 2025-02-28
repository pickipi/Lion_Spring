package com.example.securityexam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserInfoController {
    @GetMapping("/userinfo")
    public String userInfo(){
        return "user-info"; // templates/user-info.html와 매핑될 수 있도록 정확하게 명시
    }
}
