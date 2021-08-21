package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IOC해준다.
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public User 회원찾기(String username){

        return userRepository.findByUsername(username).orElseGet(() ->{
            return new User();
        });

    }

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

    @Transactional
    public void 회원수정(User user){

        //수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        //Select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화하기 위해서!!
        //영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주거든요.

        User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원찾기 실패");
        });


        //Vaildate 체크
        if (persistance.getOauth() == null || persistance.getOauth().equals("")){
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);

            //더티체킹
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }

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
