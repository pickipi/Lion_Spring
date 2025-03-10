package com.example.oauthexam.controller;

import com.example.jwtexam.security.dto.CustomUserDetails;
import com.example.oauthexam.dto.SocialUserRequestDto;
import com.example.oauthexam.entity.SocialLoginInfo;
import com.example.oauthexam.entity.User;
import com.example.oauthexam.service.SocialLoginInfoService;
import com.example.oauthexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

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
        Optional<SocialLoginInfo> socialLoginInfoOptional = socialLoginInfoService.findByProviderAndUuidAndSocialId(
                requestDto.getProvider(), requestDto.getUuid(), requestDto.getSocialId());

        if(socialLoginInfoOptional.isPresent()){
            SocialLoginInfo socialLoginInfo = socialLoginInfoOptional.get();
            LocalDateTime now = LocalDateTime.now(); // 현재 시간 구하기
            Duration duration = Duration.between(socialLoginInfo.getCreateAt(), now); // 소셜로그인 정보가 생긴 시간과 현재 시간을 비교

            if(duration.toMinutes() > 20) {// 20분을 초과했으면
                return "redirect:/error"; // 에러페이지로 리다이렉트할 것
            }

            User savedUser = userService.saveUser(requestDto, passwordEncoder);
            request.getSession().setAttribute("user", savedUser);
            return"redirect:/info";
        }
        else{ // 정보가 없다면
            return "redirect:/error";
        }
    }

    @GetMapping("/info")
    public String info(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
        model.addAttribute("user", customUserDetails);

        return "oauth/info";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "oauth/welcome";
    }
}
