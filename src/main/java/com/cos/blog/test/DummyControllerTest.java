package com.cos.blog.test;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DummyControllerTest {

    private final UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(User user){

        System.out.println("username: " +user.getUsername());
        System.out.println("password " +user.getPassword());
        System.out.println("email: " +user.getEmail());
        System.out.println(user.toString());


        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
