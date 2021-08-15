package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    //스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
    //password 부분 처리는 알아서 함
    //username 이 DB에 있는지만 확인해주면 됨.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User principal = userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("존재하지 않은 회원입니다: " + username);
        });

        return new PrincipalDetail(principal); //시큐리티 세션에 유저 정보가 저장됨.
    }
}
