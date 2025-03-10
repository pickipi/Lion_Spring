package com.example.securityexam4.controller;

import com.example.securityexam4.domain.User;
import com.example.securityexam4.domain.UserRegisterDTO;
import com.example.securityexam4.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 0. 초기 페이지
    @GetMapping("/")
    public String home(){
        return "home";
    }

    // 0-1) 환영 페이지
    @GetMapping("/welcome")
    public String welcome(){
        return "exam4/welcome";
    }

    // 1. 회원가입 폼 요청
    @GetMapping("/signup")
    public String signUp(){

        return "/exam4/users/signup";
    }

    // 2. 회원가입 요청 - 가입하기 버튼을 눌렀을경우 userreg 를 통해 값을 가져옴
    @PostMapping("/userreg")
    public String userReg(@ModelAttribute User user){
        log.info("+++++[userreg : 사용자 요청 정보 성공적으로 전달완료]+++++");

        // 사용자가 입력한 username과 동일한 유저가 이미 존재하는지 검사
        if(userService.existsUser(user.getUsername())){
            // 이미 존재한다면
            log.info("이미 존재하는 유저 [{}] 입니다.", user.getUsername());
            return "exam4/users/userreg-error";
        }

        // 사용자가 존재하지 않는다면 받아온 User객체를 registUser()메소드를 통해 저장해줌
        userService.registUser(user);

        return "redirect:/loginform";
    }

    // 2-1. 회원가입 요청 - 가입하기 버튼을 눌렀을경우 userreg_role 를 통해 값을 가져옴
    // role 포함 데이터 가져오기
    // , @RequestParam("roles") List<String> roles) 추가 가능
    @PostMapping("/userreg_role")
    public String userRegRole(@ModelAttribute UserRegisterDTO user){
        if(userService.existsUser(user.getUsername())){
            log.info("이미 존재하는 유저 [{}] 입니다.", user.getUsername());
            return "exam4/users/userreg-error";
        }
        userService.registUser(user);

        return "redirect:/loginform";
    }

    // 3. 로그인 폼 요청
    @GetMapping("/loginform")
    public String loginForm(){

        return "exam4/users/loginform";
    }

    // 4. 내 정보 보기 페이지
    @GetMapping("/myinfo")
    public String myInfo(){
        return "exam4/myinfo";
    }
}
