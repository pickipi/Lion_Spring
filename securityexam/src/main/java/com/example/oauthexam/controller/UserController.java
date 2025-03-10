package com.example.oauthexam.controller;

import com.example.oauthexam.dto.SocialUserRequestDto;
import com.example.oauthexam.service.SocialLoginInfoService;
import com.example.oauthexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SocialLoginInfoService socialLoginInfoService;
    private final PasswordEncoder passwordEncoder;

    // 로그인 폼
    @GetMapping("/loginform")
    public String loginform(){
        return "oauth/users/loginform";
    }

    // UserService로 Password를 인코딩해서 넘기도록 함 (순환참조 문제 해결)
    // ?provider="+provider+"&socialId="+socialId+"&name="+name+... 와 같은 구조로 들어올 것
    // 따라서 @RequestParam을 이용하여 그 값들을 받아옴
    @GetMapping("/registerSocialUser")
    public String registerSocialUser(@RequestParam("provider") String provider,
                                     @RequestParam("socialId") String socialId,
                                     @RequestParam("name") String name,
                                     @RequestParam("uuid") String uuid,
                                     Model model){
        // Model에 담아주어야함 - 회원가입 페이지에 넘어갈때 이 값들을 사용할 수 있도록 하는 것
        model.addAttribute("provider", provider);
        model.addAttribute("socialId", socialId);
        model.addAttribute("name", name);
        model.addAttribute("uuid", uuid);

        return "oauth/users/registerSocialUser"; // 소셜로그인 회원가입 페이지로 보냄
    }

    @PostMapping("/saveSocialUser")
    public String saveSocialUser(@ModelAttribute SocialUserRequestDto requestDto){

    }
}
