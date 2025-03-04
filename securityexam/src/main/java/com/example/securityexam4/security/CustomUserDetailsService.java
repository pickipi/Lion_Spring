package com.example.securityexam4.security;

import com.example.securityexam4.domain.Role;
import com.example.securityexam4.domain.User;
import com.example.securityexam4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){ // 가져올 유저가 없으면
            throw new UsernameNotFoundException("[" + username + "]사용자가 없습니다.");
        }
        // 똑같은 이름의 클래스를 두번 쓸 수 없기때문에 직접 링크를 걸어 userDetails의 User를 사용하기 위함
        // UserBuilder또한 import를 직접 작성해주어야한다.
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());

        userBuilder.roles(user.getRoles().stream().map(Role::getName).toList().toArray(new String[0]));

        return userBuilder.build(); // UserDetails가 반환될 것
    }
}
