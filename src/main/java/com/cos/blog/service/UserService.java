package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IOC해준다.
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public int 회원가입(User user){

        String rawPassword = user.getPassword(); //1234
        String encPassword = encoder.encode(rawPassword); //해쉬

        user.setPassword(encPassword);

        user.setRole(RoleType.USER);

        try{
            userRepository.save(user);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("UserService 회원가입(): " + e.getMessage());
        }

        return -1;
    }

    /**
     * 서비스
     * 1. 트랜잭션 관리
     * 2. 서비스 의미 때문
     *ex) 송금 서비스
     * 송금자 금액 update() - commit
     * 수신자 금액 update() - commit
     * 한 트랜잭션에서 수행  - 서비스 : 대부분 여러 쿼리가 필요함 -> 서비스
     */
}
