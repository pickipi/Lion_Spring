package com.example.oauthexam.security;

import com.example.oauthexam.entity.SocialLoginInfo;
import com.example.oauthexam.entity.User;
import com.example.oauthexam.entity.Role;
import com.example.oauthexam.service.SocialLoginInfoService;
import com.example.oauthexam.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService; // 유저 정보를 저장하기 위함
    private final SocialLoginInfoService socialLoginInfoService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 요청 정보로부터 provider를 얻어옴
        // redirect-uri : "{baseUrl}/login/oauth2/code/{registrationId}" -> registrationId 에 github, google등이 들어올 것
        String requestURI = request.getRequestURI();
        String provider = extractProviderFromUri(requestURI);

        // provider가 없는 경로로 요청되었으면 문제 발생
        if(provider == null){
            response.sendRedirect("/");
            return;
        }

        // 문제없었으면
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) auth.getPrincipal();

        String socialId = defaultOAuth2User.getAttributes().get("id").toString();
        String name = defaultOAuth2User.getAttributes().get("name").toString();

        // 유저에 값이 저장되어있었는지 아닌지를 찾아주는 메소드
        Optional<User> userOptional = userService.findByProviderAndSocialId(provider, socialId);

        if(userOptional.isPresent()){
            // 1. 소셜로 회원가입이 된 상태일때 (=로그인을 성공한 적이 있는 유저)
            // 이 사용자가 서비스를 이미 사용한 정보가 있으면 = User에 정보가 담겨있을 것
            User user = userOptional.get();
            // 얻어온 user정보를 SecurityContextHolder에 넣어줌

            // CustomUserDetails 생성
            CustomUserDetails customUserDetails = new CustomUserDetails(user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getRoles()// Roles는 Set<Role> roles 처럼 들어가있을 것 -> 따라서 stream()으로 꺼냄
                            .stream()
                            .map(Role::getName)
                            .collect(Collectors.toList())
            );

            Authentication newAuth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuth);

            response.sendRedirect("/welcome"); // 모든 것이 수행완료되면 welcome으로 리다이렉트


        }else{
            SocialLoginInfo socialLoginInfo = socialLoginInfoService.saveSocialLoginInfo(provider, socialId);

            // 2. 소셜로 회원가입이 되지 않은 상태일때 "회원가입 페이지"로 리다이렉트
            // 사용자가 소셜로그인으로 앱에 처음들어왔을때 사용자의 정보를 앱의 Service에도 남겨두고 싶을때
            // 소셜 로그인 정보를 저장함 (?provider 형식으로 provider를 controller에 넘김, &로 넘기고 싶은 나머지 값들을 한 줄로 연결시킴
            response.sendRedirect("/registerSocialUser?provider="+provider+"&socialId="
                    +socialId+"&name="+name+"&uuid="+socialLoginInfo.getUuid());
        }
    }

    private String extractProviderFromUri(String uri) {
        // ex. localhost:8080/login/oauth2/code/github 처럼 값이 들어올 것
        if(uri == null || uri.isBlank()) {
            return null;
        }

        if(!uri.startsWith("/login/oauth2/code/")){
            return null;
        }

        // 예: /login/oauth2/code/github -> github
        // 여기서 github이라는 것만 필요하므로 split()함수로 구분해냄
        String[] segments = uri.split("/");
        return segments[segments.length - 1];
    }
}
